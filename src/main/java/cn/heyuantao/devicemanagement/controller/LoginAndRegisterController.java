package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.auth.CustomUserDetails;
import cn.heyuantao.devicemanagement.dto.APILoginRequestDTO;
import cn.heyuantao.devicemanagement.exception.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/")
public class LoginAndRegisterController {

    //@Autowired
    //private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;

    @Autowired
    AuthenticationManager authenticationManager;


    //负责对接用ajax方式提交的api的login请求
    @PostMapping("/api/v1/login")
    public ResponseEntity<?> loginAPI(@RequestBody APILoginRequestDTO apiLoginRequestDTO, HttpServletRequest httpServletRequest){
        System.out.println("This is api post information !");
        System.out.println(apiLoginRequestDTO.getUsername());
        System.out.println(apiLoginRequestDTO.getPassword());
        System.out.println(apiLoginRequestDTO.getCaptcha());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(
                apiLoginRequestDTO.getUsername(),
                apiLoginRequestDTO.getPassword()
        );
        Authentication authentication;
        try{
            authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }catch (Exception ex){
            ErrorDetails errorDetails=new ErrorDetails("登录失败",ex.getMessage());
            return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
        }
        //usernamePasswordAuthenticationToken.setDetails(authenticationDetailsSource.buildDetails(httpServletRequest));
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok("success");
    }
}
