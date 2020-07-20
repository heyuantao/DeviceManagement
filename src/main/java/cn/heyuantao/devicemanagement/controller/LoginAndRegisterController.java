package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.auth.CustomUserDetails;
import cn.heyuantao.devicemanagement.dto.APILoginRequestDTO;
import cn.heyuantao.devicemanagement.exception.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/")
public class LoginAndRegisterController {

    @Autowired
    AuthenticationManager authenticationManager;

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

    //负责对接用ajax方式提交的api的login请求
    @PostMapping("/api/v1/login")
    public ResponseEntity<?> loginAPI(@RequestBody APILoginRequestDTO apiLoginRequestDTO){
        System.out.println("This is api post information !");
        System.out.println(apiLoginRequestDTO.getUsername());
        System.out.println(apiLoginRequestDTO.getPassword());
        System.out.println(apiLoginRequestDTO.getCaptcha());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(
                apiLoginRequestDTO.getUsername(),
                apiLoginRequestDTO.getPassword()
        );
        try{
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }catch (Exception ex){
            ErrorDetails errorDetails=new ErrorDetails("登录失败",ex.getMessage());
            return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("success");
    }
}
