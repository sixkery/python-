package com.sixkery.kike.api.configuration;

import com.sixkery.kike.api.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;


/**
 * security 安全配置
 *
 * @author: sixkery
 * @date:2021/4/1
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    private UserServiceImpl userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }


    /**
     * 使用 security 默认的加密方式
     *
     * @return 加密类
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
