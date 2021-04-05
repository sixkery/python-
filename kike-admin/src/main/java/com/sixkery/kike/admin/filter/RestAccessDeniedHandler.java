package com.sixkery.kike.admin.filter;

import com.alibaba.fastjson.JSON;
import com.sixkery.kike.common.response.ApiResponses;
import com.sixkery.kike.common.response.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 没有权限访问处理
 *
 * @author sixkery
 * @date 2020/11/19
 */
//@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e)
            throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(JSON.toJSONString(ApiResponses.failed(ResultCode.FORBIDDEN, "无权访问！")));
    }
}
