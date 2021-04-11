package com.sixkery.kike.api.configuration;

import com.sixkery.kike.api.configuration.security.JwtAuthenticationEntryPoint;
import com.sixkery.kike.api.configuration.security.JwtAuthenticationFilter;
import com.sixkery.kike.api.configuration.security.JwtAuthenticationTokenFilter;
import com.sixkery.kike.api.configuration.security.RestAccessDeniedHandler;
import com.sixkery.kike.api.service.UserServiceImpl;
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
 * security 安全配置
 *
 * @author: sixkery
 * @date:2021/4/1
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    private UserServiceImpl userService;

    @Resource
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Resource
    private RestAccessDeniedHandler restAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // 前后端分离项目不创建 session 使用 token 禁用缓存

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().headers().cacheControl();
        http.formLogin().loginProcessingUrl("/login");
        // 前端跨域请求会先进行一次 options 请求 // 允许对于网站静态资源的无授权访问
        http.authorizeRequests().antMatchers(HttpMethod.GET,
                "/",
                "/*.html",
                "/favicon.ico",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js",
                "/swagger-resources/**",
                "/v2/api-docs/**"
        ).permitAll().antMatchers(HttpMethod.OPTIONS).permitAll();
        // 任何请求都需要认证
        http.authorizeRequests().anyRequest().authenticated();
        // 对登录注册要允许匿名访问;

        // 拦截账号密码 覆盖 UsernamePasswordAuthenticationFilter 过滤器
        http.addFilterAt(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        // 处理异常情况：认证失败和权限不足
        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedHandler(restAccessDeniedHandler)
                .and().addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);


    }

    /**
     * 配置使用 默认的数据库查询用户信息
     *
     * @param auth 认证管理器
     * @throws Exception 异常
     */
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

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public RestAccessDeniedHandler restAuthenticationEntryPoint() {

        return new RestAccessDeniedHandler();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }


}
