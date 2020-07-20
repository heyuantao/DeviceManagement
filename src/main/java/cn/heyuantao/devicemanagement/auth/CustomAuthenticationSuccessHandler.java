package cn.heyuantao.devicemanagement.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author he_yu
 * 设置认证成功后，根据用户的类型重定向到不同的URL
 * 当前认证成功后，使用auth.getPrincipal()获得了类型为UserAuthPrincipal的对象，这个对象是对数据库中对象的一个封装
 * 可以依据该对象来判断用户的类型和权限，从而进行登陆后地址的转跳
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();
        if(customUserDetails.getUsername().equals("admin")){
            redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,"/");
        }else{
            redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,"/user");
        }
    }
}
