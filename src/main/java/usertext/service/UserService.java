package usertext.service;

import java.util.List;
import java.util.Map;

public interface UserService {
    String userAdd(String user);//添加用户
    List<Map<String, Object>> groupFilterUsers(int ageThreshold);//查询
}
