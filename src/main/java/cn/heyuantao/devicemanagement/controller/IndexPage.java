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

/**
 * @author he_yu
 * 首页信息
 */
@Controller
@RequestMapping("/")
public class IndexPage {

    @GetMapping
    public String home(Model model){
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
}
