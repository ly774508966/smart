package com.rabbit.smart.aop;

import com.alibaba.fastjson.JSONObject;
import com.rabbit.smart.dao.entity.SysOperationLog;
import com.rabbit.smart.service.SysOperationLogService;
import com.rabbit.smart.service.SysPermissionService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 切面记录所有业务请求
 */
@Component
@Aspect
public class ControllerAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysOperationLogService logService;
    @Autowired
    private SysPermissionService permissionService;

    private Map<String, String> urlNameMaps;

    @PostConstruct
    public void init() {
        urlNameMaps = permissionService.queryUrlNameMap();
    }

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
        int cost = (int) (end - start);
        txtLog(request, object, cost);
        dbLog(request, cost);
        return object;
    }

    private void txtLog(HttpServletRequest request, Object object, int cost) {
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
    }

    private void dbLog(HttpServletRequest request, int cost) {
        String url = request.getRequestURI();
        String params = JSONObject.toJSONString(request.getParameterMap());
        Object permissionName = urlNameMaps.get(url);
        if (permissionName != null && SecurityUtils.getSubject().isAuthenticated()) {
            SysOperationLog log = new SysOperationLog();
            log.setCost(cost);
            log.setAccount(SecurityUtils.getSubject().getPrincipal().toString());
            log.setDescription(String.format("参数：%s", params));
            log.setPermissionName(permissionName.toString());
            log.setSuccess(true);
            logService.addLog(log);
        }
    }
}
