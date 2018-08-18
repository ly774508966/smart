package com.rabbit.smart.controller.admin;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "right", tags = "权限管理")
@RestController
@RequestMapping("right")
public class PermissionController {
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
