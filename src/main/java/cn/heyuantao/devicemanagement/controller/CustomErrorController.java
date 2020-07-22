package cn.heyuantao.devicemanagement.controller;
import cn.heyuantao.devicemanagement.auth.CustomUserDetails;
import cn.heyuantao.devicemanagement.dto.OwnerResponseDTO;
import cn.heyuantao.devicemanagement.exception.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author he_yu
 */

@RestController
@RequestMapping("/error")
public class CustomErrorController implements ErrorController{

    @RequestMapping
    public ResponseEntity<ErrorDetails> handleError(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == 401){
            ErrorDetails errorDetails=new ErrorDetails("拒绝访问","你没有权限访问该接口");
            return new ResponseEntity(errorDetails, HttpStatus.UNAUTHORIZED);
        }else if(statusCode == 404){
            ErrorDetails errorDetails=new ErrorDetails("页面不存在","该页面不存在");
            return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
        }else if(statusCode == 403){
            ErrorDetails errorDetails=new ErrorDetails("禁止访问","你没有权限访问该接口");
            return new ResponseEntity(errorDetails, HttpStatus.FORBIDDEN);
        }else {
            ErrorDetails errorDetails = new ErrorDetails("服务器出错", "服务器出错");
            return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public String getErrorPath() {
        return null;
    }
}

