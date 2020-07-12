package cn.heyuantao.devicemanagement.exception;

import lombok.Data;
import org.springframework.validation.BindingResult;

/**
 * @author he_yu
 */

@Data
public class RequestValidateException extends RuntimeException {
    /*This is validate result from controller */
    private BindingResult bindingResult;

    public RequestValidateException(BindingResult bindingResult){
        this.bindingResult=bindingResult;
    }

    @Override
    public String getMessage() {
        return this.bindingResult.getFieldError().getDefaultMessage();
    }
}
