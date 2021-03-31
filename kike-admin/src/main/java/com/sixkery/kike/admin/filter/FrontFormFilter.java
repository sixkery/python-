package com.sixkery.kike.admin.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 拦截 Login 获取 username password 交由 authenticationManager 处理
 *
 * @author sixkery
 * @date 2020/10/27
 */
public class FrontFormFilter extends AbstractAuthenticationProcessingFilter {

    public FrontFormFilter() {
        // 拦截 login 的 POST 的请求
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        // 从 json 中提取 username password
        String body = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        JSONObject jsonObject = JSON.parseObject(body);
        Object username = jsonObject.get("username");
        Object password = jsonObject.get("password");
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, password);

        return this.getAuthenticationManager().authenticate(auth);
    }
}
