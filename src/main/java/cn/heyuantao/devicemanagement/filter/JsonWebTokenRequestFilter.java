package cn.heyuantao.devicemanagement.filter;

import cn.heyuantao.devicemanagement.auth.CustomUserDetailsService;
import cn.heyuantao.devicemanagement.util.JsonWebTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author he_yu
 * 用于过滤请求，并识别所携带的JWT信息
 */
@Component
public class JsonWebTokenRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JsonWebTokenUtil jsonWebTokenUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = httpServletRequest.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if( (authorizationHeader !=null) && StringUtils.startsWithIgnoreCase(authorizationHeader,"Bearer ") ){
            jwt = StringUtils.substring(authorizationHeader,7);
            username = jsonWebTokenUtil.extractUsername(jwt);
        }

        if( username!=null && SecurityContextHolder.getContext().getAuthentication() == null ){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(jsonWebTokenUtil.validateToken(jwt, userDetails.getUsername())){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
