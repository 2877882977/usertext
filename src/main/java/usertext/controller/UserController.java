package usertext.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import usertext.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @GetMapping
    public String user(){
        return "hellow";
    }

    @RequestMapping(value = "/ageThreshold", method = RequestMethod.GET)
    public List<Map<String, Object>> groupAndFilterUsers(int ageThreshold){
        return userService.groupFilterUsers(ageThreshold);
    }
}
