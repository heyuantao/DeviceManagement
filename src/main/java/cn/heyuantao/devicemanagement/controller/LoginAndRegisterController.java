/*
package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.auth.CustomUserDetails;
import cn.heyuantao.devicemanagement.domain.User;
import cn.heyuantao.devicemanagement.dto.AuthRequestDTO;
import cn.heyuantao.devicemanagement.dto.AuthResponseDTO;
import cn.heyuantao.devicemanagement.dto.UserResponseDTO;
import cn.heyuantao.devicemanagement.exception.ErrorDetails;
import cn.heyuantao.devicemanagement.exception.RequestParamValidateException;
import cn.heyuantao.devicemanagement.util.JsonWebTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


*/
/**
 * @author he_yu
 * 处理登录和注销
 *//*

@Api(tags = {"登录接口"})
@RestController
@RequestMapping("/")
public class LoginAndRegisterController {

    @Autowired
    AuthenticationManager authenticationManager;

    */
/**
     * 输入用户名和密码，返回Jwt Token
     * @param authRequestDTO
     * @return
     *//*

    @ApiOperation(value = "登录接口",notes = "根据用户名和密码返回JWT")
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

        AuthResponseDTO authResponseDTO = new AuthResponseDTO(token);

        return new ResponseEntity(authResponseDTO,HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "用户状态",notes = "用携带JWT的请求来获取用户信息")
    @GetMapping("/api/v1/status")
    public ResponseEntity<UserResponseDTO> status(HttpServletRequest httpServletRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = customUserDetails.getUser();
        UserResponseDTO userResponseDTO = new UserResponseDTO(user);

        return new ResponseEntity(userResponseDTO,HttpStatus.ACCEPTED);
    }


*/
/*    @GetMapping("/api/v1/hello")
    public ResponseEntity<String> hello(){
        return new ResponseEntity("hello",HttpStatus.ACCEPTED);
    }*//*


}
*/
