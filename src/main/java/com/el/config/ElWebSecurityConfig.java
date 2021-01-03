package com.el.config;

import com.el.handler.ElLoginSuccessHandler;
import com.el.handler.ElSessionExpireHandler;
import com.el.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @author:superJar
 * @date:2020/12/31
 * @time:12:21
 * @details:
 */
@Configuration
@EnableWebSecurity
public class ElWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private ElLoginSuccessHandler elLoginSuccessHandler;

    @Resource
    private UserServiceImpl userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                .loginPage("/login.html")
//                .loginPage("/login")
                .loginProcessingUrl("/login")
//                .loginProcessingUrl("/login")
                .successHandler(elLoginSuccessHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/login.html","/login").permitAll()
                .antMatchers("/index").authenticated()
                .anyRequest().access("@rbacService.hasPermission(request,authentication)")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .invalidSessionUrl("/login.html")
                .sessionFixation()
                .migrateSession()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(new ElSessionExpireHandler());

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**","/login");
    }

    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();
    }
}
