package cn.heyuantao.devicemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author he_yu
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/index","/css/*","/js/*","/swagger-ui.html**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        List<UserDetails> userDetails=new ArrayList<>();
        userDetails.add(User.withDefaultPasswordEncoder().username("hyt").password("123").roles("USER","ADMIN").build());
        userDetails.add(User.withDefaultPasswordEncoder().username("test").password("123").roles("USER").build());
        return new InMemoryUserDetailsManager(userDetails);

        //return super.userDetailsService();
    }

}
