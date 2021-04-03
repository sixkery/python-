package com.sixkery.kike.admin.config;

import com.sixkery.kike.admin.filter.JwtAuthenticationTokenFilter;
import com.sixkery.kike.admin.filter.RestAccessDeniedHandler;
import com.sixkery.kike.admin.service.impl.CustomerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @author: sixkery
 * @date:2021/4/1
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Resource
    private CustomerServiceImpl customerService;


    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /**
     * 自定义无权限访问拦截器
     */
    @Resource
    private RestAccessDeniedHandler restAccessDeniedHandler;


    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable()
                // 前后端分离是 STATELESS，故 session 使用该策略
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // OPTIONS请求全部放行
                .and().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                //登录和注册的接口放行，其他接口全部接受验证.antMatchers("/druid/**").anonymous()
                .antMatchers("/login/**").permitAll()
                .anyRequest().authenticated()
                .and().headers().cacheControl();

        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.exceptionHandling().accessDeniedHandler(restAccessDeniedHandler);
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerService).passwordEncoder(passwordEncoder());

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
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
