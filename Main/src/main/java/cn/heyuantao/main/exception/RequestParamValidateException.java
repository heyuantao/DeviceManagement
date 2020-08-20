package cn.heyuantao.main.exception;

import org.springframework.validation.BindingResult;

/**
 * @author he_yu
 * 该异常类用于处理在controller层面的参数校验异常问题
 * 如果发现参数错误则抛出异常，最终该异常被全局异常处理函数俘获
 */

public class RequestParamValidateException extends RuntimeException {
    /*This is validate result from controller */
    private BindingResult bindingResult;

    public RequestParamValidateException(BindingResult bindingResult){
        this.bindingResult=bindingResult;
    }

    @Override
    public String getMessage() {
        return this.bindingResult.getFieldError().getDefaultMessage();
    }
}
