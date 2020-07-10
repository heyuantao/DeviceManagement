package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.service.impl.UserServiceImpl;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class PageController {

    @Resource
    private UserServiceImpl userService;

    @RequestMapping("/dologin")
    public String dologin(String username,String password){
        try {
            System.out.println("---------登录成功-----------");
            return "index";
        } catch (Exception e) {
            System.out.println("---------登录失败-----------");
            return "login";
        }
    }

    @RequestMapping("/login.html")
    public String login(){
        return "login";
    }

    @RequestMapping("/")
    public String welcome(){
        return "login";
    }

    @RequestMapping("/index.html")
    public String index(Model model){
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(){
        System.out.println("-------用户注销----------");
        return "login";
    }
}
