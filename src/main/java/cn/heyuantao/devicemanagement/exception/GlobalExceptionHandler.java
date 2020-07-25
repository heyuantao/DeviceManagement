package cn.heyuantao.devicemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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
        ErrorDetails errorDetails = new ErrorDetails("该信息未找到 !",exception.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    // 全局异常处理，处理未知的错误
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails("发现错误", exception.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 全局异常处理，处理接口层面的数据异常的错误
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(ControllerValidateException.class)
    public ResponseEntity<?> handleRequestParamValidateException(ControllerValidateException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(),"Request 数据校验错误");
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * 全局异常处理，处理服务层面的数据异常
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(ServiceValidateException.class)
    public ResponseEntity<?> handleServiceValidateException(ServiceValidateException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(),"Service 层数据校验错误");
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * 全局异常处理处理数据库入库时的问题
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(DatabaseValidateException.class)
    public ResponseEntity<?> handleDatabaseValidateException(DatabaseValidateException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(),"Database 层数据校验错误");
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // 全局异常处理，处理API接口接受数据解析出错的异常，当传入的json数据语法出现问题时发生
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails("输入数据格式错误", exception.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    //方法不支持的异常，当对模型接口发送了不被支持的方法时候会触发该异常
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails("接口不支持该方法", exception.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    //处理JWT验证异常
/*    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ResponseEntity> handleExpiredJwtException(ExpiredJwtException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails("接口权限异常", exception.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.UNAUTHORIZED);
    }*/

}
