package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.exception.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * @author he_yu
 */
@Slf4j
@RestController
@RequestMapping("/error")
public class CustomErrorController implements ErrorController{

    /**根据错误码返回相应的状态数据，状态数据以JSON的方式返回
     * @param request
     * @return
     */
    @RequestMapping
    public ResponseEntity<ErrorDetails> handleError(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        if(statusCode == HttpStatus.UNAUTHORIZED.value()){
            ErrorDetails errorDetails=new ErrorDetails("拒绝访问","Http Status Code 401");
            return new ResponseEntity(errorDetails, HttpStatus.UNAUTHORIZED);
        }else if(statusCode == HttpStatus.FORBIDDEN.value()){
            ErrorDetails errorDetails=new ErrorDetails("禁止访问","Http Status Code 403");
            return new ResponseEntity(errorDetails, HttpStatus.FORBIDDEN);
        }else if(statusCode == HttpStatus.NOT_FOUND.value() ){
            ErrorDetails errorDetails=new ErrorDetails("页面不存在","Http Status Code 404");
            return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
        }else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            ErrorDetails errorDetails = new ErrorDetails("服务器出错", "Http Status Code 500");
            return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            /**
             * 遇到了不在计划中的错误，将信息返回
             */
            log.error("Error happend in CustomErrorController.handleError !");
            ErrorDetails errorDetails = new ErrorDetails("服务器出错", "Http Status Code "+statusCode);
            return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}

