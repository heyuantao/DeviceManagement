package cn.heyuantao.main.auth;

import cn.heyuantao.main.domain.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author he_yu
 */
@Data
public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        super();
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
/*        return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));*/
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        List<String> roleStringList = new ArrayList<>();
        roleStringList.add("ROLE_ADMIN");
        roleStringList.add("ROLE_USER");
        for(String roleString:roleStringList){
            grantedAuthorityList.add(new SimpleGrantedAuthority(roleString));
        }
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
