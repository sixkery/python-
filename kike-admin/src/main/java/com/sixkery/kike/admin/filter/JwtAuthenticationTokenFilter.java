package com.sixkery.kike.admin.filter;

import cn.hutool.core.util.StrUtil;
import com.sixkery.kike.common.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

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
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 从 request 中获取 token
        String header = request.getHeader(jwtUtil.getHeader());
        if (StrUtil.isNotEmpty(header)) {
            String username = jwtUtil.getUsernameFromToken(header);
            if (StrUtil.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() != null) {
                // 查询数据库 获得用户名密码
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // 验证 token 是否正确
                if (jwtUtil.validateToken(header, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

        }
        chain.doFilter(request, response);

    }

}
