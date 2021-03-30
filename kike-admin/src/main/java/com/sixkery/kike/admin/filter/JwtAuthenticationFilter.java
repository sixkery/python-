package com.sixkery.kike.admin.filter;

import com.sixkery.kike.common.response.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义 JWT 过滤器处理登录的操作
 *
 * @author sixkery
 * @date 2020/10/27
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * 过滤器一定要设置 AuthenticationManager 这里的 AuthenticationManager 从 Security 的配置中传入
     *
     * @param authenticationManager 认证管理器
     */
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        // 运行父类 UsernamePasswordAuthenticationFilter 的构造方法，能够设置此滤器指定
        super();
        setAuthenticationManager(authenticationManager);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // 从 POST 请求中获取用户名密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        // 设置一些客户 IP 啥信息，后面想用的话可以用，虽然没啥用
        setDetails(request, token);

        // 交给 AuthenticationManager 进行鉴权
        return getAuthenticationManager().authenticate(token);

    }

    /**
     * 鉴权成功进行的操作，设置返回加密后的 token
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        handleResponse(request, response, authResult, null);
    }

    /**
     * 鉴权失败进行的操作，返回 用户名或密码错误 的信息
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        handleResponse(request, response, null, failed);
    }

    private void handleResponse(HttpServletRequest request, HttpServletResponse response, Authentication authResult,
                                AuthenticationException failed) throws IOException, ServletException {
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        if (authResult != null) {
            // 处理登入成功请求
            response.setStatus(HttpStatus.OK.value());
        } else {
            // 处理登入失败请求
            ApiResponses.failed("登录成功！");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }
}
