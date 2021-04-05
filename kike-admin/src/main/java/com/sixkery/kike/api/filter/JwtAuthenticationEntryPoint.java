package com.sixkery.kike.api.filter;

import com.alibaba.fastjson.JSON;
import com.sixkery.kike.common.response.ApiResponses;
import com.sixkery.kike.common.response.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义拦截器 解决匿名用户无权限无资源访问异常
 *
 * @author sixkery
 * @date 2020/11/19
 */
//@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {
        // 当用户没有携带凭证访问 需要认证的资源时调用此方法
        response.getWriter().write(JSON.toJSONString(ApiResponses.failed(ResultCode.TOKEN_NOTFOUND, "没有携带凭证！")));

    }
}
