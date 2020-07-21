package cn.heyuantao.devicemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginAndRegisterPage {

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }


    @RequestMapping("/index")
    public String index(Model model){
        return "index";
    }


    @GetMapping("/logout-success")
    public String logoutSuccess(Model model){
        return "logout";
    }

}
