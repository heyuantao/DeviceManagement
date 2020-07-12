package cn.heyuantao.devicemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


/**
 * @author he_yu
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    // custom exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails("Resource not Found !",exception.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    // 全局异常处理，处理未知的错误
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(ResourceNotFoundException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails("Global exception happen !", exception.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 全局异常处理，处理接口层面的数据异常的错误
    @ExceptionHandler(RequestParamValidateException.class)
    public ResponseEntity<?> handleRequestParamValidateException(RequestParamValidateException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails("数据校验错误", exception.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 全局异常处理，处理服务层面的数据异常
    @ExceptionHandler(ServiceParamValidateException.class)
    public ResponseEntity<?> handleServiceValidateException(ServiceParamValidateException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails("数据校验错误", exception.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
