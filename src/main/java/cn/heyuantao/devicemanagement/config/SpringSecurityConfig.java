package cn.heyuantao.devicemanagement.config;

import cn.heyuantao.devicemanagement.auth.CustomAuthenticationFailureHandler;
import cn.heyuantao.devicemanagement.auth.CustomAuthenticationSuccessHandler;
import cn.heyuantao.devicemanagement.auth.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author he_yu
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui.html","/null/swagger-resources","/swagger-resources/**","/v2/api-docs").permitAll()
                .antMatchers("/login","/api/v1/login").permitAll()
                .antMatchers("/api/v1/captcha","/api/v1/captcha/check").permitAll()
                .antMatchers("/","/index","/css/*","/js/*","/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                //.addFilterBefore(validateCaptchaFilter, UsernamePasswordAuthenticationFilter.class)
                //.formLogin().loginPage("/login").permitAll()
                //.successHandler(customAuthenticationSuccessHandler).failureHandler(customAuthenticationFailureHandler)
                //.and()
                .logout().invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").permitAll();
                //.logoutSuccessUrl("/logout-success").permitAll();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
