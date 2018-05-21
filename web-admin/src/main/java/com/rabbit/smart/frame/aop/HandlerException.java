package com.rabbit.smart.frame.aop;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class HandlerException {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public void ExceptionHandler(Exception ex) {
        HttpServletRequest r = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String request = String.format("请求异常:%s %s %s %s", r.getRemoteHost(), r.getMethod(), r.getRequestURI(), JSONObject.toJSONString(r.getParameterMap()));
        logger.error(request, ex);//异常记录
    }
}
