package cn.heyuantao.devicemanagement.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author he_yu
 * 专门处理验证码验证错误的异常
 */
public class ValidateCaptchaException  extends AuthenticationException {
    public ValidateCaptchaException(String message) {
        super(message);
    }
}
