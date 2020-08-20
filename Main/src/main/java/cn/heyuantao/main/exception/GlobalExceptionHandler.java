package cn.heyuantao.main.exception;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * @author he_yu
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理，处理未知的错误
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request){
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        exception.printStackTrace(printWriter);
        log.error("--------------------Exception Info Begin-------------------------");
        log.error(stringWriter.toString());
        log.error("--------------------Exception Info End---------------------------");

        ErrorDetails errorDetails = new ErrorDetails("发现错误", exception.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * 当指定的对象没有找到时，将会抛出该异常
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails("该信息未找到 !",exception.getMessage());

        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }





    /**
     * 全局异常处理，处理接口层面的数据异常的错误
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(RequestParamValidateException.class)
    public ResponseEntity<?> handleRequestParamValidateException(RequestParamValidateException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(),"Request 数据校验错误");
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }


    /**
     * 全局异常处理，处理服务层面的数据异常
     * 在服务层面接受控制器传过来的以POJO格式做为参数的对象，这个对象的数据如果出现不一致则要在该层抛出异常
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(ServiceParamValidateException.class)
    public ResponseEntity<?> handleServiceValidateException(ServiceParamValidateException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(),"Service 层数据校验错误");
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }


    /**
     * 全局异常处理，处理API接口接受数据解析出错的异常，当传入的json数据语法出现问题时发生
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails("输入数据格式错误", exception.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }


    /**
     * 方法不支持的异常，当对模型接口发送了不被支持的方法时候会触发该异常
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails("接口不支持该方法", exception.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }


    /**
     * 处理JWT验证异常
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException exception,WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails("接口权限异常", exception.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 处理 PreAuthorize 异常
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException exception,WebRequest request){
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        exception.printStackTrace(printWriter);
        log.error("--------------------AccessDeniedException Info Begin-------------------------");
        log.error(stringWriter.toString());
        log.error("--------------------AccessDeniedException Info End---------------------------");

        ErrorDetails errorDetails = new ErrorDetails("该角色用户禁止访问", exception.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.UNAUTHORIZED);
    }

}
