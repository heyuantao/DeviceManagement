package cn.heyuantao.devicemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


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

    // global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(ResourceNotFoundException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails("Global exception happen !", exception.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RequestValidateException.class)
    public ResponseEntity<?> handleRequestValidateException(RequestValidateException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails("Validate exception happen !", exception.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
