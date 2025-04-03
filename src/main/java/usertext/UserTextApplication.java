package usertext;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import usertext.service.UserService;
import usertext.serviceimpl.UserServiceImpl;

@SpringBootApplication
@MapperScan({"usertext.basic.dao"})
@ComponentScan({"usertext.*"})
public class UserTextApplication {
    public static void main(String[] args) {
        ApplicationContext context=SpringApplication.run(UserTextApplication.class, args);
        UserService userService = context.getBean(UserServiceImpl.class);
//        String userInfo = "[{username:张三,email:zhangsan@163.com,passwordHash:123456,registrationDate:2025-04-03,phoneNumber:12345678901,address:广东省广州市天河区1栋,age:25,idCardNumber:123456789010123456}," +
//                "{username:李四,email:lisi@163.com,passwordHash:12345612,registrationDate:2025-04-02,phoneNumber:12345678901,address:广东省广州市天河区2栋,age:24,idCardNumber:123456789010223456}," +
//                "{username:小白,email:xiaobai@163.com,passwordHash:12345622,registrationDate:2025-04-01,phoneNumber:12345678901,address:广东省广州市天河区3栋,age:26,idCardNumber:123456789013333456}," +
//                "{username:小红,email:xiaohong@163.com,passwordHash:12345632,registrationDate:2025-04-02,phoneNumber:12345678901,address:广东省广州市天河区4栋,age:30,idCardNumber:123456789045673456}]";
        String userInfo = "[{\"username\":\"张三\",\"email\":\"zhangsan@163.com\",\"passwordHash\":\"123456\",\"registrationDate\":\"2025-04-03\",\"phoneNumber\":\"12345678901\",\"address\":\"广东省广州市天河区1栋\",\"age\":25,\"idCardNumber\":\"123456789010123456\"}," +
                "{\"username\":\"李四\",\"email\":\"lisi@163.com\",\"passwordHash\":\"12345612\",\"registrationDate\":\"2025-04-02\",\"phoneNumber\":\"12345678901\",\"address\":\"广东省广州市天河区2栋\",\"age\":24,\"idCardNumber\":\"553456789010223456\"}," +
                "{\"username\":\"小白\",\"email\":\"xiaobai@163.com\",\"passwordHash\":\"12345622\",\"registrationDate\":\"2025-04-05\",\"phoneNumber\":\"12345678901\",\"address\":\"广东省广州市天河区30栋\",\"age\":26,\"idCardNumber\":\"883456789013333456\"}," +
                "{\"username\":\"小红\",\"email\":\"xiaohong@163.com\",\"passwordHash\":\"12345632\",\"registrationDate\":\"2025-04-02\",\"phoneNumber\":\"12345678901\",\"address\":\"广东省广州市天河区34栋\",\"age\":30,\"idCardNumber\":\"993456789045673456\"}]";
//        userService.userAdd(userInfo);
    }

}
