package com.sixkery.kike.api.configuration;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixkery.kike.api.constant.SecurityConstant;
import com.sixkery.kike.api.util.JwtUtil;
import com.sixkery.kike.common.exception.ApiException;
import com.sixkery.kike.common.response.ApiResponses;
import com.sixkery.kike.common.response.ResultCode;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

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


    @SuppressWarnings("unchecked")
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // 不是 post 请求抛出异常
        if (!request.getMethod().equals(SecurityConstant.POST)) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        // 判断请求是否是 json 格式，如果不是直接调用父类
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            // 把 request 的 json 数据转换为 Map 提前 username password
            Map<String, String> authenticationBean = null;
            UsernamePasswordAuthenticationToken authRequest = null;
            try (InputStream is = request.getInputStream()) {
                authenticationBean = new ObjectMapper().readValue(is, Map.class);
            } catch (IOException e) {
                e.printStackTrace();
                throw new ApiException(e.getMessage());
            }

            if (!authenticationBean.isEmpty()) {
                String username = authenticationBean.get(SPRING_SECURITY_FORM_USERNAME_KEY);
                String password = authenticationBean.get(SPRING_SECURITY_FORM_PASSWORD_KEY);
                // 将账号密码放入到 UsernamePasswordAuthenticationToken 中认证
                authRequest = new UsernamePasswordAuthenticationToken(username, password);
                this.setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            } else {
                return null;
            }


        } else {
            return super.attemptAuthentication(request, response);
        }

    }

    /**
     * 鉴权成功进行的操作，设置返回加密后的 token
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        // 将生成的 authentication 放入容器中，生成安全的上下文
        SecurityContextHolder.getContext().setAuthentication(authResult);

        // 生成 token
        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.generateToken(userDetails);

        ApiResponses.print(response,ApiResponses.success(token, "登录成功！"));


    }

    /**
     * 鉴权失败进行的操作，返回 用户名或密码错误 的信息
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {


        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSON.toJSONString(ApiResponses.failed(ResultCode.FAILED, "登录失败！")));
    }


}
