package com.rabbit.smart.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 */
@Api(value = "user", tags = "用户管理")
@RestController
@RequestMapping("user")
public class UserController {
    //region 增删改查
    @RequestMapping(method = RequestMethod.POST)
    public void add() {

    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void remove() {

    }

    @RequestMapping(method = RequestMethod.PUT)
    public void modify() {

    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public void query() {

    }
    //endregion
}
