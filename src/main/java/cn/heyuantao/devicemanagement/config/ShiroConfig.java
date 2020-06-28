package cn.heyuantao.devicemanagement.config;

import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

//https://www.bilibili.com/video/BV1pa4y1471s?p=8

@Configuration
public class ShiroConfig {

    //@Bean
    /*public IniRealm getIniReam(){
        return new IniRealm("classpath:shiro.ini");
    }*/
    @Bean(name = "CustomRealm")
    public CustomRealm getRealm(){
        return new CustomRealm();
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(CustomRealm customRealm){      //IniRealm iniRealm
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm);         //iniRealm
        return securityManager;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        filter.setSecurityManager(securityManager);

        Map<String,String> filterMap = new HashMap<>();
        /*
        filterMap.put("/","anon");
        filterMap.put("/dologin","anon");
        filterMap.put("/login.html","anon");
        filterMap.put("/register.html","anon");
        filterMap.put("/static/**","anon");
        filterMap.put("/index.html","anon");

         */
        filterMap.put("/**","anon");


        filter.setFilterChainDefinitionMap(filterMap);
        filter.setLoginUrl("/login.html");
        filter.setUnauthorizedUrl("/login.html");
        return filter;
    }
}
