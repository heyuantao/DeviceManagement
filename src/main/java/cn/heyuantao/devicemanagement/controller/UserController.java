package cn.heyuantao.devicemanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    //public String
    @RequestMapping("/users")
    public String hello(){
        return "hello world !";
    }
}
