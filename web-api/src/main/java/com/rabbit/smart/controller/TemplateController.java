package com.rabbit.smart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 创建模板，创建新的controller文件时，可以直接拷贝
 */
//@Api(value = "template", tags = "创建模板")
//@RestController
//@RequestMapping("template")
public class TemplateController {
    //region 增删改查
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Void> add() {
        //TODO 需要开发
        return null;
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public ResponseEntity<Void> remove() {
        //TODO 需要开发
        return null;
    }

    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public ResponseEntity<Void> modify() {
        //TODO 需要开发
        return null;
    }

    @RequestMapping(value = "query", method = RequestMethod.POST)
    public ResponseEntity<Void> query() {
        //TODO 需要开发
        return null;
    }

    @RequestMapping(value = "get", method = RequestMethod.POST)
    public ResponseEntity<Void> get() {
        //TODO 需要开发
        return null;
    }
    //endregion
}
