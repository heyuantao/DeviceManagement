package cn.heyuantao.devicemanagement.auth;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author he_yu
 */
public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    protected CustomAuthenticationFilter() {
        super(new AntPathRequestMatcher("/api/v1/login","POST"));
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        if(!StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(),"POST")){
            throw new AuthenticationServiceException("认证请求方法不支持");
        }

        return null;
    }
}
