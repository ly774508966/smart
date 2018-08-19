package com.rabbit.smart.controller.admin;

import com.rabbit.smart.dao.entity.SysPermission;
import com.rabbit.smart.dto.Recursion;
import com.rabbit.smart.service.SysPermissionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "permission", tags = "权限管理")
@RestController
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private SysPermissionService permissionService;

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

    @RequestMapping(value = "query/tree", method = RequestMethod.POST)
    public ResponseEntity<Recursion<SysPermission>> query_tree() {
        Recursion<SysPermission> tree = permissionService.queryTree();
        return new ResponseEntity(tree, HttpStatus.OK);
    }
    //endregion
}