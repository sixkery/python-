package com.sixkery.kike.admin.config;

import com.sixkery.kike.admin.service.impl.CustomerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @author: sixkery
 * @date:2021/4/1
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Resource
    private CustomerServiceImpl customerService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerService).passwordEncoder(passwordEncoder());

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
