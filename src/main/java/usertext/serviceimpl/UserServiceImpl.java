package usertext.serviceimpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import usertext.basic.dao.UserExtraInfoMapper;
import usertext.basic.dao.UserMapper;
import usertext.basic.entity.User;
import usertext.basic.entity.UserExtraInfo;
import usertext.service.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserExtraInfoMapper userExtraInfoMapper;

    //添加用户信息处理与数据库基本操作
    @Override
    public  String userAdd(String userinfo){

        //用户排序，按注册日期从早到晚的顺序进行排序
        JsonArray jsonArray = JsonParser.parseString(userinfo).getAsJsonArray();
        JsonArray sortedJsonArray = StreamSupport.stream(jsonArray.spliterator(),false)
                .map(JsonElement::getAsJsonObject)
                .sorted(Comparator.comparing(obj -> obj.get("registrationDate").getAsString()))
                .collect(JsonArray::new,JsonArray::add,JsonArray::addAll);
//        System.out.println(sortedJsonArray);


        try{
            ObjectMapper  objectMapper = new ObjectMapper();
            String jsonString = sortedJsonArray.toString();
            List<User> userList = objectMapper.readValue(jsonString, new TypeReference<List<User>>(){});
            for(User user : userList){
                //加密密码BCrypt
                String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
                user.setPasswordHash(hashed);
                //提取数据到UserExtraInfo
                UserExtraInfo  userExtraInfo = new UserExtraInfo();
                userExtraInfo.setAge(user.getAge());
                userExtraInfo.setAddress(user.getAddress());
                userExtraInfo.setPhoneNumber(user.getPhoneNumber());
                userExtraInfo.setIdCardNumber(user.getIdCardNumber());
                //邮箱校验是否已存在
                User UserEmail = userMapper.selectByUserEmail(user.getEmail());
                if(UserEmail == null){
                    System.out.println("insert 用户信息");
                    userMapper.insert(user);
                    User UserAdd = userMapper.selectByUserEmail(user.getEmail());
                    int id = UserAdd.getUserId();
                    userExtraInfo.setUserId(id);
                    userExtraInfoMapper.insert(userExtraInfo);
                }else{
                    System.out.println("upd 用户信息");
                    int id = UserEmail.getUserId();
                    user.setUserId(id);
                    userExtraInfo.setUserId(id);
                    userMapper.userUpd(user);
                    userExtraInfoMapper.userEIUpd(userExtraInfo);
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return userinfo;
        //       密码 //解密
//        if (BCrypt.checkpw("111111", hashed))
//            System.out.println("密码正确1");
//        else
//            System.out.println("密码错误1");
    }
    //groupFilterUsers接口 用于筛选用户年龄。
    @Override
    public List<Map<String, Object>> groupFilterUsers(int ageThreshold){
        System.out.println(ageThreshold);
        return userMapper.groupAndFilterUser(ageThreshold);
    }
    //groupAge 用于查询用户平均年龄。
    @Override
    public List<Map<String, Object>> groupAverAge(int ageThreshold){
        System.out.println(ageThreshold);
        return userMapper.groupAverAgeFilterUser(ageThreshold);
    }
    //groupAge 用于查询用户年龄的最大注册日期。
    @Override
    public List<Map<String, Object>> groupMaxData(int ageThreshold){
        System.out.println(ageThreshold);
        return userMapper.maximumRegistrationDate(ageThreshold);
    }

}
