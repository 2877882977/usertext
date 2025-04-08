package usertext.basic.dao;

import org.apache.ibatis.annotations.Mapper;
import usertext.basic.entity.User;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserEmail(String email);
    int userUpd(User user);
    List<Map<String, Object>> groupAndFilterUser(int ageThreshold);
    List<Map<String, Object>> groupAverAgeFilterUser(int ageThreshold);
    List<Map<String, Object>> maximumRegistrationDate(int ageThreshold);
}