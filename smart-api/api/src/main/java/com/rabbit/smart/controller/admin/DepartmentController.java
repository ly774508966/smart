package com.rabbit.smart.controller.admin;

import com.rabbit.smart.dao.entity.SysDepartment;
import com.rabbit.smart.dao.entity.SysPermission;
import com.rabbit.smart.dao.entity.SysRole;
import com.rabbit.smart.dto.Recursion;
import com.rabbit.smart.service.SysDepartmentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 部门管理
 */
@Api(value = "department", tags = "部门管理")
@RestController
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private SysDepartmentService departmentService;

    //region 增删改查
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void add() {
        //TODO 需要开发
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public void remove() {
        //TODO 需要开发
    }

    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public void modify() {
        //TODO 需要开发
    }

    @RequestMapping(value = "query/tree", method = RequestMethod.POST)
    public ResponseEntity<Recursion<SysDepartment>> query_tree() {
        Recursion<SysDepartment> roles = departmentService.queryTree();
        return new ResponseEntity(roles, HttpStatus.OK);
    }
    //endregion
}
