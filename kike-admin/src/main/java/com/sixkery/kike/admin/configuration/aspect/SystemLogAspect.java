package com.sixkery.kike.admin.configuration.aspect;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * @author sixkery
 */
@Aspect
@Configuration
@Slf4j
public class SystemLogAspect {


    /**
     * 切入点
     */
    @Pointcut("execution(* com.sixkery.kike.admin.web.*(..)))")
    public void systemLog() {
    }

    /**
     * 在切入点之前
     *
     * @param joinPoint 对象
     */
    @Before("systemLog()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 打印请求相关参数
        log.info("========================================== Start ==========================================");
        // 打印请求 url
        log.info("URL            : {}", request.getRequestURL().toString());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.info("请求参数     : {}", arg);
        }

    }

    /**
     * 环绕通知
     *
     * @param joinPoint 对象
     * @return 结果
     * @throws Throwable 异常
     */
    @Around("systemLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印出参
        log.info("出参数据        : {}", JSON.toJSON((result)));
        // 执行耗时
        log.info("执行耗时        : {} ms", System.currentTimeMillis() - startTime);
        return result;
    }

    /**
     * 在切点之后织入
     */
    @After("systemLog()")
    public void doAfter() {
        log.info("=========================================== End ===========================================");
        // 每个请求之间空一行
        log.info("");
    }


}
