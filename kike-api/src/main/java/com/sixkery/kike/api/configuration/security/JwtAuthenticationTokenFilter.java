package com.sixkery.kike.api.configuration.security;

import cn.hutool.core.util.StrUtil;
import com.sixkery.kike.api.constant.SecurityConstant;
import com.sixkery.kike.api.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 处理每一次的鉴权，验证令牌是否合法
 *
 * @author sixkery
 * @date 2020/11/8
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 从 request 中获取 token
        String token = request.getHeader(SecurityConstant.AUTHORIZATION);
        if (StrUtil.isNotEmpty(token)) {
            // The part after "Bearer "
            String authToken = token.substring("Bearer ".length());
            String username = jwtUtil.getUsernameFromToken(authToken);
            log.info("检验的用户名：{}", username);
            if (StrUtil.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {

                // 查询数据库 获得用户名密码
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // 验证 token 是否正确
                if (jwtUtil.validateToken(authToken, userDetails)) {

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    log.info("认证的用户:{}", username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

        }
        chain.doFilter(request, response);

    }

}
