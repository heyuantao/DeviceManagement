package cn.heyuantao.devicemanagement.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author he_yu
 */
public class CustomAuthenticationToken extends AbstractAuthenticationToken {

    private String username;
    private String password;
    private String captcha;

    public CustomAuthenticationToken(String username,String password,String captcha){
        super(null);
        this.username = username;
        this.password = password;
        this.captcha = captcha;
    }
/*    public CustomAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }*/

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }
}
