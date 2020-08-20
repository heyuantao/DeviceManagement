package cn.heyuantao.main.service;

import cn.heyuantao.main.auth.CustomUserDetails;
import cn.heyuantao.main.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 该服务定义了基本的权限框架，配合在接口上使用@PreAuthorize注解来完成权限的检查，样例如下
 * @PreAuthorize("@ss.testPermission()")
 * 该模块未完全实现，请勿使用
 * @author he_yu
 */
@Service("ss")
public class PermissionService {


    /**
     * 使用 Spring Security 获取当前的用户信息
     * @return
     */
    private User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = customUserDetails.getUser();
        return user;
    }


    /**
     * 使用用户的名字来检查相应的权限
     * @param permission
     * @return
     */
    public Boolean hasPermission(String permission){
        User user = getCurrentUser();
        System.out.println("Check permission in preauthorize !");
        if(user==null) {
            return Boolean.FALSE;
        }else if(StringUtils.equalsIgnoreCase(user.getName(),"admin")){
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

    /**
     * 仅仅是测试这种方法是否能用，配合@PreAuthorize放在控制器上来使用，例子如下
     * @PreAuthorize("@ss.testPermission()")
     * @return
     */
    public Boolean testPermission(){
        return Boolean.FALSE;
    }
}
