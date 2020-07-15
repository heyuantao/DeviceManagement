package cn.heyuantao.devicemanagement.auth;

import cn.heyuantao.devicemanagement.domain.User;
import cn.heyuantao.devicemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
@Service
public class UserAuthPrincipalDetailsService implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //User user = this.userService.getUserByName(username);
        //return new UserAuthPrincipal(user);
        return null;
    }
}
*/
