package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.auth.CustomUserDetails;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
        //AnonymousAuthenticationToken
        String usernameForDisplay = "";

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth instanceof AnonymousAuthenticationToken){
            System.out.println("匿名用户");
            usernameForDisplay = "访客";
        }else if(auth instanceof UsernamePasswordAuthenticationToken){
            System.out.println("系统注册用户");
            CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();
            System.out.println(customUserDetails.getUsername());
            usernameForDisplay= customUserDetails.getUsername();
        }else{
            System.out.println("系统未知类型用户");
        }
        model.addAttribute("username",usernameForDisplay);
        //String username = (String)auth.getPrincipal();
        //AbstractAuthenticationToken token = SecurityContextHolder.getContext().getAuthentication();
        //User user = (User) SecurityContextHolder.getContext().getAuthentication();
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
