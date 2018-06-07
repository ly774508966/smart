package com.rabbit.smart.core.aop;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class ControllerAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 拦截
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Around("execution(!void com..*Controller.*(..))")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        //日志记录
        long start = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        long end = System.currentTimeMillis();
        long cost = end - start;
        String result = String.format(
                "请求:%s 响应:%s 用时:%s ms",
                String.format("%s %s %s %s", request.getRemoteHost(), request.getMethod(), request.getRequestURI(), JSONObject.toJSONString(request.getParameterMap())),
                object instanceof String ? object : JSONObject.toJSONString(object),
                cost
        );

        if (cost > 1000) {
            logger.warn(result);
        } else {
            logger.info(result);
        }

        return object;
    }
}
