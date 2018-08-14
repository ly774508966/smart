package com.rabbit.smart.core.aop;

import com.alibaba.fastjson.JSONObject;
import com.rabbit.smart.core.protocol.Response;
import com.rabbit.smart.core.protocol.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class HandlerException {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity ExceptionHandler(Exception ex) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String info = String.format("请求异常:%s %s %s %s", request.getRemoteHost(), request.getMethod(), request.getRequestURI(), JSONObject.toJSONString(request.getParameterMap()));
        logger.error(info, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
