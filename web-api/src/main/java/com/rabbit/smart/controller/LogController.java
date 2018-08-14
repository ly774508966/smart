package com.rabbit.smart.controller;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志管理
 */
@Api(value = "log", tags = "日志管理")
@RestController
@RequestMapping("log")
public class LogController {
    @RequestMapping(value = "operation", method = RequestMethod.GET)
    public ResponseEntity<Void> query_operation() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ResponseEntity<Void> query_login() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
