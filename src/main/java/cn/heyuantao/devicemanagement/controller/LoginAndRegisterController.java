package cn.heyuantao.devicemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginAndRegisterController {


/*    @RequestMapping("/login")
    public String dologin(String username,String password){
        try {
            System.out.println("---------登录成功-----------");
            return "index";
        } catch (Exception e) {
            System.out.println("---------登录失败-----------");
            return "login";
        }
    }*/

    @RequestMapping("/login")
    public String login(Model model){
        return "login";
    }

/*    @RequestMapping("/registration")
    public String register(Model model){
        return "login";
    }*/

    @RequestMapping("/")
    public String welcome(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(Model model){
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(){
        System.out.println("-------用户注销----------");
        return "login";
    }
}
