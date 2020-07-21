package cn.heyuantao.devicemanagement.filter;

import cn.heyuantao.devicemanagement.auth.CustomUserDetailsService;
import cn.heyuantao.devicemanagement.exception.ErrorDetails;
import cn.heyuantao.devicemanagement.util.JsonWebTokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import java.util.HashMap;

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
        ErrorDetails errorDetails = null;

        try{
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
        }catch (ExpiredJwtException ex){
            errorDetails = new ErrorDetails("登录过期","请检查JWT是否过期");
            responseWithErrorDetails(httpServletResponse,errorDetails,HttpStatus.UNAUTHORIZED);
            return;
        }catch (Exception ex){
            errorDetails = new ErrorDetails("未知错误","登录出现未知错误");
            responseWithErrorDetails(httpServletResponse,errorDetails,HttpStatus.UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }


    private void responseWithErrorDetails(HttpServletResponse httpServletResponse, ErrorDetails errorDetails, HttpStatus httpStatus) throws IOException {
        String content = null;

        ObjectMapper mapper = new ObjectMapper();
        if (errorDetails == null) {
            content = mapper.writeValueAsString(new HashMap<>());
        }else{
            content = mapper.writeValueAsString(errorDetails);
        }

        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setStatus(httpStatus.value());
        httpServletResponse.getWriter().print(content);
    }

}
