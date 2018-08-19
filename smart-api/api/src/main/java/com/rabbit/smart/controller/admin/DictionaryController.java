package com.rabbit.smart.controller.admin;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 字典管理
 */
@Api(value = "dictionary", tags = "字典管理")
@RestController
@RequestMapping("dictionary")
public class DictionaryController {
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
