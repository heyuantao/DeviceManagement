package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.dto.APILoginRequestDTO;
import cn.heyuantao.devicemanagement.exception.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
