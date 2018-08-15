package com.rabbit.smart.aop;

import com.alibaba.fastjson.JSONObject;
import com.rabbit.smart.dao.entity.SysOperationLog;
import com.rabbit.smart.service.SysOperationLogService;
import com.rabbit.smart.service.SysPermissionService;
import com.rabbit.smart.util.param.ParamError;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class HandlerException {
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

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> ExceptionHandler(Exception ex) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        dbLog(request, ex);
        if (ex instanceof ParamError) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } else {
            txtLog(ex, request);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private void txtLog(Exception ex, HttpServletRequest request) {
        String info = String.format("请求异常:%s %s %s %s", request.getRemoteHost(), request.getMethod(), request.getRequestURI(), JSONObject.toJSONString(request.getParameterMap()));
        logger.error(info, ex);
    }

    private void dbLog(HttpServletRequest request, Exception ex) {
        String url = request.getRequestURI();
        String params = JSONObject.toJSONString(request.getParameterMap());
        Object permissionName = urlNameMaps.get(url);
        if (permissionName != null && SecurityUtils.getSubject().isAuthenticated()) {
            SysOperationLog log = new SysOperationLog();
            log.setCost(0);
            log.setAccount(SecurityUtils.getSubject().getPrincipal().toString());
            log.setDescription(String.format("参数：%s\r\n%s", params, ex.getMessage()));
            log.setPermissionName(permissionName.toString());
            log.setSuccess(false);
            logService.addLog(log);
        }
    }

}
