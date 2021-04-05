package com.sixkery.kike.api.config;

import com.sixkery.kike.api.filter.JwtAuthenticationTokenFilter;
import com.sixkery.kike.api.filter.RestAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
//
//
//    @Autowired
//    private UserDetailsService userDetailsService;


    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /**
     * 自定义无权限访问拦截器
     */
    @Resource
    private RestAccessDeniedHandler restAccessDeniedHandler;


//    @Override
//    public void configure(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity.csrf().disable()
//                // 前后端分离是 STATELESS，故 session 使用该策略
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().formLogin().loginProcessingUrl("/login")
//                .and().csrf().disable();
//        // 替换默认的 form
//        httpSecurity.addFilterAt(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
                .withUser("sixkery")
                .password(new BCryptPasswordEncoder().encode("123")).roles("admin");
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

//    @Override
//    @Bean
//    public AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }

//    @Bean
//    public UsernamePasswordAuthenticationFilter jwtAuthenticationFilter() throws Exception {
//        return new JwtAuthenticationFilter(this.authenticationManager());
//    }


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
