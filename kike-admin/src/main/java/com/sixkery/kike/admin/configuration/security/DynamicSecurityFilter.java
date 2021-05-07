package com.sixkery.kike.admin.configuration.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * 基于路径的动态权限过滤器
 *
 * @author sixkery
 * @date 2020/11/19
 */
public class DynamicSecurityFilter extends AbstractSecurityInterceptor implements Filter {

    @Resource
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        FilterInvocation filterInvocation = new FilterInvocation(servletRequest, servletResponse, filterChain);

        // options 请求直接放行
        if (request.getMethod().equals(HttpMethod.OPTIONS.toString())) {
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
            return;
        }
        // 白名单放行
        PathMatcher pathMatcher = new AntPathMatcher();
        for (String url : ignoreUrlsConfig.getUrls()) {
            if (pathMatcher.match(url, request.getRequestURI())) {
                filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
                return;
            }
        }
        //
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return null;
    }
}
