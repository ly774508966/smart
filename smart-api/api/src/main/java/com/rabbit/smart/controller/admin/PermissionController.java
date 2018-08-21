package com.rabbit.smart.controller.admin;

import com.rabbit.smart.dao.entity.SysPermission;
import com.rabbit.smart.dao.entity.SysPermissionExample;
import com.rabbit.smart.dao.mapper.SysPermissionMapper;
import com.rabbit.smart.dto.Recursion;
import com.rabbit.smart.dto.in.PermissionAddDto;
import com.rabbit.smart.dto.in.PermissionModifyDto;
import com.rabbit.smart.service.SysPermissionService;
import com.rabbit.smart.util.param.Validator;
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
    @Autowired
    private SysPermissionMapper permissionMapper;

    //region 增删改查
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Void> add(PermissionAddDto params) {
        //校验
        Validator.checkNotNull(params.getCode(), "权限编号");
        Validator.checkNotNull(params.getName(), "权限名称");
        Validator.checkNotNull(params.getParentCode(), "父权限编号");
        Validator.checkNotNull(params.getType(), "权限类型");

        //填参
        SysPermission permission = new SysPermission();
        permission.setCode(params.getCode());
        permission.setName(params.getName());
        permission.setParentCode(params.getParentCode());
        permission.setUrl(params.getUrl());
        permission.setType(params.getType());
        permission.setIsMenuOpen(params.getMenuOpen());
        permission.setMenuSort(params.getMenuSort());
        permission.setMenuIcon(params.getMenuIcon());
        permission.setIsLog(params.getLog());
        permission.setDescription(params.getDescription());
        permission.setStatus(1);

        permissionMapper.insertSelective(permission);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public ResponseEntity<Void> remove(Integer id) {
        //逻辑删除
        Validator.checkNotNull(id, "权限编号");
        SysPermission permission = new SysPermission();
        permission.setId(id);
        permission.setStatus(2);
        permissionMapper.updateByPrimaryKeySelective(permission);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public ResponseEntity<Void> modify(PermissionModifyDto params) {
        //校验
        Validator.checkNotNull(params.getCode(), "权限编号");
        Validator.checkNotNull(params.getName(), "权限名称");
        Validator.checkNotNull(params.getParentCode(), "父权限编号");
        Validator.checkNotNull(params.getType(), "权限类型");

        //条件
        SysPermissionExample example = new SysPermissionExample();
        example.createCriteria().andCodeEqualTo(params.getCode());

        //待更新对象
        SysPermission permission = new SysPermission();
        permission.setCode(params.getCode());
        permission.setName(params.getName());
        permission.setParentCode(params.getParentCode());
        permission.setUrl(params.getUrl());
        permission.setType(params.getType());
        permission.setIsMenuOpen(params.getMenuOpen());
        permission.setMenuSort(params.getMenuSort());
        permission.setMenuIcon(params.getMenuIcon());
        permission.setIsLog(params.getLog());
        permission.setDescription(params.getDescription());
        permission.setStatus(1);

        permissionMapper.updateByExampleSelective(permission, example);

        return new ResponseEntity(HttpStatus.OK);
    }
    //endregion

    @RequestMapping(value = "query/tree", method = RequestMethod.POST)
    public ResponseEntity<Recursion<SysPermission>> query_tree() {
        Recursion<SysPermission> tree = permissionService.queryTree();
        return new ResponseEntity(tree, HttpStatus.OK);
    }

    @RequestMapping(value = "query/tree/simple", method = RequestMethod.POST)
    public ResponseEntity<Recursion<SysPermission>> query_tree_simple() {
        Recursion<SysPermission> tree = permissionService.queryTreeSimple();
        return new ResponseEntity(tree, HttpStatus.OK);
    }
}
