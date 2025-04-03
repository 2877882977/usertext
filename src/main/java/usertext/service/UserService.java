package usertext.service;

import java.util.List;
import java.util.Map;

public interface UserService {
    String userAdd(String user);
    List<Map<String, Object>> groupFilterUsers(int ageThreshold);
}
