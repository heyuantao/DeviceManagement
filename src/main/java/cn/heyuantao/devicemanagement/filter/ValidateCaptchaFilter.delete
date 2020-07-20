package cn.heyuantao.devicemanagement.filter;

import cn.heyuantao.devicemanagement.auth.CustomAuthenticationFailureHandler;
import cn.heyuantao.devicemanagement.exception.ValidateCaptchaException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author he_yu
 * 对验证码进行处理，验证用户输入的验证码是否与在会话中存储的验证码是否一致
 */
/*
@Component
public class ValidateCaptchaFilter extends OncePerRequestFilter {

    @Autowired
    AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        if( StringUtils.equalsIgnoreCase("/login",httpServletRequest.getRequestURI())&&
                StringUtils.equalsIgnoreCase("POST",httpServletRequest.getMethod()) ){
            try{
                validateCaptcha(httpServletRequest);
            }catch (ValidateCaptchaException e){
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse, e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void validateCaptcha(HttpServletRequest httpServletRequest) {
        HttpSession httpSession= httpServletRequest.getSession();
        String captchaInSession = (String) httpSession.getAttribute("captcha");
        String captchaInRequest = httpServletRequest.getParameter("captcha");
        System.out.println("Captcha in session and in request is !");
        System.out.println(captchaInSession);
        System.out.println(httpServletRequest.getParameterMap().toString());
        if(StringUtils.isBlank(captchaInRequest)){
            throw new ValidateCaptchaException("验证码为空");
        }
        if(captchaInSession==null){
            throw new ValidateCaptchaException("验证码不存在");
        }
        if(!StringUtils.equalsIgnoreCase(captchaInRequest,captchaInSession)){
            throw new ValidateCaptchaException("验证码不正确！");
        }
        httpSession.removeAttribute("captcha");
    }
}
*/
