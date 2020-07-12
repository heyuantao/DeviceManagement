package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.domain.User;
import cn.heyuantao.devicemanagement.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Resource
    UserService userService;

    //@RequestMapping("/hello")
    @GetMapping
    public List<User> list(){
        List<User> userList=userService.getUsers();
        return userList;
    }
}
