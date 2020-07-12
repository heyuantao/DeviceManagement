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
        return new ResponseEntity("Resource not Found !", HttpStatus.NOT_FOUND);
    }

    // global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(ResourceNotFoundException exception, WebRequest request){
        return new ResponseEntity("Global exception happen !", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
