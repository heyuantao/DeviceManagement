package cn.heyuantao.main.auth;

import cn.heyuantao.main.domain.User;
import cn.heyuantao.main.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author he_yu
 * 根据传入的用户名对数据库进行查找，如果查找失败会抛出异常
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        try{
            user = this.userService.getUserByName(username);
        }catch (Exception ex){
            throw new UsernameNotFoundException("未找到该用户");
        }
        return new CustomUserDetails(user);
    }
}
