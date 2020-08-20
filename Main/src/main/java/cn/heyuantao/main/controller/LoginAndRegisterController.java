package cn.heyuantao.main.controller;

import cn.heyuantao.main.auth.CustomUserDetails;
import cn.heyuantao.main.domain.User;
import cn.heyuantao.main.dto.AuthRequestDTO;
import cn.heyuantao.main.dto.AuthResponseDTO;
import cn.heyuantao.main.dto.UserResponseDTO;
import cn.heyuantao.main.exception.ErrorDetails;
import cn.heyuantao.main.exception.RequestParamValidateException;
import cn.heyuantao.main.util.JsonWebTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author he_yu
 * 处理登录和注销
 */
@Api(tags = {"登录接口"})
@RestController
@RequestMapping("/")
public class LoginAndRegisterController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserController userController;

    /**
     * 输入用户名和密码，返回Jwt Token
     * @param authRequestDTO
     * @return
     */
    @ApiOperation(value = "登录接口",notes = "根据用户名和密码返回JWT")
    @PostMapping("/api/v1/login")
    public ResponseEntity<AuthResponseDTO> loginAPI(
            @Validated @RequestBody AuthRequestDTO authRequestDTO,
            BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new RequestParamValidateException(bindingResult);
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(),authRequestDTO.getPassword());

        try{
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
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
        //UserResponseDTO userResponseDTO = new UserResponseDTO(user);
        UserResponseDTO userResponseDTO = userController.convertToDTO(user);

        return new ResponseEntity(userResponseDTO,HttpStatus.ACCEPTED);
    }

}
