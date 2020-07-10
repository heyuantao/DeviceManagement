package cn.heyuantao.devicemanagement.service.impl;

import cn.heyuantao.devicemanagement.domain.User;
import cn.heyuantao.devicemanagement.mapper.UserMapper;
import cn.heyuantao.devicemanagement.service.UserService;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    }
}