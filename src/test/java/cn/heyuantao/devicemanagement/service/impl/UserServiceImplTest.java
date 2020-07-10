package cn.heyuantao.devicemanagement.service.impl;

import cn.heyuantao.devicemanagement.domain.User;
import cn.heyuantao.devicemanagement.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    void getUsers() {
        List<User> userList = userService.getUsers();
        System.out.println("Show users !");
        for(User oneUser:userList){
            System.out.println(oneUser);
        }
    }
}