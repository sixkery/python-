/*
 * All rights Reserved, Designed By www.kerrylogistics.com
 *
 * @version v1.0
 * @Title: SystemLogAspect
 * @Package: com.kerry.ktms.order.domain.config
 * @Description: 切控制器上的日志
 * @author: www.desmart.com.cn
 * @date: 2021/4/8
 * @Copyright: 2021嘉里大通物流有限公司. All rights reserved.
 * 注意：本内容仅限于嘉里大通物流有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.kerry.ktms.order.domain.config;


import com.alibaba.fastjson.JSON;
import com.kerry.ktms.order.api.verify.ValidList;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


/**
 * @author sixkery
 */
@Aspect
@Configuration
@Slf4j
public class SystemLogAspect {


    @Pointcut("execution(* com.kerry.ktms.order.api..KappKoms2KtmsController.*(..)))")
    public void systemLog() {
    }

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
            if (arg instanceof ValidList) {
                val list = ((ValidList) arg).getList();
                log.info("对接KAPP，KOMS入参 = {}", JSON.toJSONString(list));
            } else {
                log.info("请求参数     : {}", arg);
            }
        }


    }

    @Around("systemLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        log.info("Class Method   : {}.{}", proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName());
        // 打印出参
        log.info("出参数据      : {}", JSON.toJSON((result)));
        // 执行耗时
        log.info("执行耗时      : {} ms", System.currentTimeMillis() - startTime);
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
