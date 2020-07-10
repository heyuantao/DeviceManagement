package cn.heyuantao.devicemanagement.service.impl;

import cn.heyuantao.devicemanagement.domain.User;
import cn.heyuantao.devicemanagement.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
//@MybatisTest
//@AutoConfigureTestDatabase
//@ContextConfiguration("classpath:/applicationContext.xml")
class UserServiceImplTest {

    @Resource
    UserService userService;

    @Test
    void getUsers() {
        List<User> userList = userService.getUsers();
        System.out.println("Show users !");
        for(User oneUser:userList){
            System.out.println(oneUser);
        }
        System.out.println("Finished !");
    }

    @Test
    void createUsers() {
        User oneUser= new User();
        oneUser.setName("zua");
        oneUser.setPassword("zua");
        oneUser.setEmail("zua@example.com");
        oneUser.setSuperuser(true);
        User createdUser = userService.addUser(oneUser);
        System.out.println(createdUser);
    }
}