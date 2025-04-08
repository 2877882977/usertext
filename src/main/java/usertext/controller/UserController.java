package usertext.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import usertext.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/")
public class UserController {
    @Resource
    private UserService userService;
    //    @GetMapping
//    public String user(){
//        return "hellow";
//    }

    //接口设计：创建一个接口groupAndFilterUsers，该接口接收一个int类型的ageThreshold参数，用于筛选用户年龄。
    @RequestMapping(value = "/groupAndFilterUsers", method = RequestMethod.GET)
    public List<Map<String, Object>> groupAndFilterUsers(@RequestParam int ageThreshold){
        return userService.groupFilterUsers(ageThreshold);
    }

    //接口设计：创建一个接口groupAverageAgeFilterUsers，该接口接收一个int类型的ageThreshold参数，用于筛选用户平均年龄。
    @RequestMapping(value = "/groupAgeFilterUsers", method = RequestMethod.GET)
    public List<Map<String, Object>> groupAverageAgeFilterUsers(@RequestParam int ageThreshold){
        return userService.groupAverAge(ageThreshold);
    }
    //接口设计：创建一个接口groupMaxDataFilterUsers，该接口接收一个int类型的ageThreshold参数，用于筛选用户年龄的最大注册日期。
    @RequestMapping(value = "/groupMaxDataFilterUsers", method = RequestMethod.GET)
    public List<Map<String, Object>> groupMaxDataFilterUsers(@RequestParam int ageThreshold){
        return userService.groupMaxData(ageThreshold);
    }

}
