package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.auth.CustomUserDetails;
import cn.heyuantao.devicemanagement.dto.APILoginRequestDTO;
import cn.heyuantao.devicemanagement.exception.ErrorDetails;
import cn.heyuantao.devicemanagement.exception.ValidateCaptchaException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


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

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(
                apiLoginRequestDTO.getUsername(),
                apiLoginRequestDTO.getPassword()
        );

        HttpSession httpSession= httpServletRequest.getSession();
        String captchaInSession = (String) httpSession.getAttribute("captcha");
        String captchaInRequest = apiLoginRequestDTO.getCaptcha();

        if(StringUtils.isBlank(captchaInRequest)){
            ErrorDetails errorDetails=new ErrorDetails("验证码为空","");
            return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
        }
        if(captchaInSession==null){
            ErrorDetails errorDetails=new ErrorDetails("验证码为空","");
            return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
        }
        if(!StringUtils.equalsIgnoreCase(captchaInRequest,captchaInSession)){
            ErrorDetails errorDetails=new ErrorDetails("验证码错误","");
            return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
        }

        Authentication authentication;
        try{
            authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }catch (Exception ex){
            ErrorDetails errorDetails=new ErrorDetails("登录失败",ex.getMessage());
            return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
        }
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity(new HashMap<>(),HttpStatus.ACCEPTED);
    }

    @PostMapping("/api/v1/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response,
                                    Authentication authentication){

        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, authentication);
        return new ResponseEntity(new HashMap<>(),HttpStatus.ACCEPTED);
    };
}
