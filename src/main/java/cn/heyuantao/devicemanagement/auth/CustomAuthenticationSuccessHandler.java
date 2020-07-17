package cn.heyuantao.devicemanagement.auth;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author he_yu
 * 设置认证成功后，根据用户的类型重定向到不同的URL
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserAuthPrincipal userAuthPrincipal= (UserAuthPrincipal) auth.getPrincipal();
        if(userAuthPrincipal.getUsername().equals("admin")){
            redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,"/admin");
        }else{
            redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,"/user");
        }
    }
}
