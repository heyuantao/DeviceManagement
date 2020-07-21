package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.dto.AuthRequestDTO;
import cn.heyuantao.devicemanagement.dto.AuthResponseDTO;
import cn.heyuantao.devicemanagement.exception.ErrorDetails;
import cn.heyuantao.devicemanagement.exception.RequestParamValidateException;
import cn.heyuantao.devicemanagement.util.JsonWebTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author he_yu
 * 处理登录和注销
 */
@RestController
@RequestMapping("/")
public class LoginAndRegisterController {

    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * 输入用户名和密码，返回Jwt Token
     * @param authRequestDTO
     * @return
     */
    @PostMapping("/api/v1/login")
    public ResponseEntity<AuthResponseDTO> loginAPI(
            @Validated @RequestBody AuthRequestDTO authRequestDTO,
            BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new RequestParamValidateException(bindingResult);
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(
                authRequestDTO.getUsername(),
                authRequestDTO.getPassword()
        );

        Authentication authentication;
        try{
            authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }catch (Exception ex){
            ErrorDetails errorDetails=new ErrorDetails("登录失败",ex.getMessage());
            return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
        }

        JsonWebTokenUtil jsonWebTokenUtil = new JsonWebTokenUtil();
        String token = jsonWebTokenUtil.generateToken(authRequestDTO.getUsername());

        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setJwt(token);

        return new ResponseEntity(authResponseDTO,HttpStatus.ACCEPTED);
    }

/*    @GetMapping("/api/v1/hello")
    public ResponseEntity<String> hello(){
        return new ResponseEntity("hello",HttpStatus.ACCEPTED);
    }*/

}
