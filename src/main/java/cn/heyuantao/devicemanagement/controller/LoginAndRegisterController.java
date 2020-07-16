package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.auth.UserAuthPrincipal;
import cn.heyuantao.devicemanagement.domain.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginAndRegisterController {

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

/*    @RequestMapping("/registration")
    public String register(Model model){
        return "login";
    }*/

    @RequestMapping("/")
    public String home(Model model){
        //UserAuthPrincipal user = (UserAuthPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "index";
    }

    @RequestMapping("/index")
    public String index(Model model){
        return "index";
    }

/*    @GetMapping("/logout")
    public String logout(Model model){
        return "logout";
    }*/

    @GetMapping("/logout-success")
    public String logoutSuccess(Model model){
        return "logout";
    }
}
