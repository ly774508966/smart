package com.rabbit.smart.controller.admin;

import com.rabbit.smart.dao.entity.SysDepartment;
import com.rabbit.smart.dao.entity.SysPermission;
import com.rabbit.smart.dao.entity.SysRole;
import com.rabbit.smart.dao.mapper.SysDepartmentMapper;
import com.rabbit.smart.dto.Recursion;
import com.rabbit.smart.dto.in.DepartmentAddDto;
import com.rabbit.smart.dto.in.DepartmentModifyDto;
import com.rabbit.smart.service.SysDepartmentService;
import com.rabbit.smart.util.param.Validator;
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
    @Autowired
    private SysDepartmentMapper departmentMapper;

    //region 增删改查
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Void> add(DepartmentAddDto params) {
        Validator.checkNotNull(params.getParentId(), "父部门编号");
        Validator.checkNotNull(params.getName(), "部门名称");
        SysDepartment department = new SysDepartment();
        department.setParentId(params.getParentId());
        department.setName(params.getName());
        department.setDescription(params.getDescription());
        departmentMapper.insertSelective(department);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public ResponseEntity<Void> remove(Integer id) {
        departmentMapper.deleteByPrimaryKey(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public ResponseEntity<Void> modify(DepartmentModifyDto params) {
        Validator.checkNotNull(params.getId(), "部门编号");
        Validator.checkNotNull(params.getParentId(), "父部门编号");
        Validator.checkNotNull(params.getName(), "部门名称");
        SysDepartment department = new SysDepartment();
        department.setId(params.getId());
        department.setParentId(params.getParentId());
        department.setName(params.getName());
        department.setDescription(params.getDescription());
        departmentMapper.updateByPrimaryKeySelective(department);
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "query/tree", method = RequestMethod.POST)
    public ResponseEntity<Recursion<SysDepartment>> query_tree() {
        Recursion<SysDepartment> roles = departmentService.queryTree();
        return new ResponseEntity(roles, HttpStatus.OK);
    }
    //endregion
}
