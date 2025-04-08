package usertext.service;

import java.util.List;
import java.util.Map;

public interface UserService {
    String userAdd(String user);//添加用户

    List<Map<String, Object>> groupFilterUsers(int ageThreshold);//筛选用户年龄

    List<Map<String, Object>> groupAverAge(int ageThreshold);//查询用户平均年龄
    List<Map<String, Object>> groupMaxData(int ageThreshold);//查询用户年龄的最大注册日期
}
