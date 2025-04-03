package usertext.serviceimpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
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
    @Override

    public  String userAdd(String userinfo){
        System.out.println(userinfo);


        JsonArray jsonArray = JsonParser.parseString(userinfo).getAsJsonArray();
        JsonArray sortedJsonArray = StreamSupport.stream(jsonArray.spliterator(),false)
                .map(JsonElement::getAsJsonObject)
                .sorted(Comparator.comparing(obj -> obj.get("registrationDate").getAsString()))
                .collect(JsonArray::new,JsonArray::add,JsonArray::addAll);
        System.out.println(sortedJsonArray);
        try{
            ObjectMapper  objectMapper = new ObjectMapper();

            String jsonString = sortedJsonArray.toString();
            List<User> userList = objectMapper.readValue(jsonString, new TypeReference<List<User>>(){});
            for(User user : userList){
                //加密密码
                String hashed = BCrypt.hashpw(user.getPasswordHash(), BCrypt.gensalt());
                user.setPasswordHash(hashed);
                //提取数据
                UserExtraInfo  userExtraInfo = new UserExtraInfo();
                userExtraInfo.setAge(user.getAge());
                userExtraInfo.setAddress(user.getAddress());
                userExtraInfo.setPhoneNumber(user.getPhoneNumber());
                userExtraInfo.setIdCardNumber(user.getIdCardNumber());
//                邮箱校验
                User UserEmail = userMapper.selectByUserEmail(user.getEmail());
                if(UserEmail == null){
                    userMapper.insert(user);
                    User UserAdd = userMapper.selectByUserEmail(user.getEmail());
                    int id = UserAdd.getUserId();
                    userExtraInfo.setUserId(id);
                    userExtraInfoMapper.insert(userExtraInfo);
                }else{
                    System.out.println("upd");
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

        //        //解密
//        if (BCrypt.checkpw("111111", hashed))
//            System.out.println("密码正确1");
//        else
//            System.out.println("密码错误1");

        return userinfo;
    }
    public List<Map<String, Object>> groupFilterUsers(@RequestParam int ageThreshold){
        System.out.println(ageThreshold);
        return userMapper.groupAndFilterUser(ageThreshold);
    }
}
