package com.rabbit.smart.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志管理
 */
@Api(value = "op_log", tags = "操作日志管理")
@RestController
@RequestMapping("op_log")
public class OperationLogController {
    @RequestMapping(method = RequestMethod.GET)
    public void query() {

    }
}
