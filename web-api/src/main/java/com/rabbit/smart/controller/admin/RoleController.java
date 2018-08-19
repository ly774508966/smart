package com.rabbit.smart.controller.admin;

import com.rabbit.smart.dao.entity.SysRole;
import com.rabbit.smart.dao.mapper.SysRoleMapper;
import com.rabbit.smart.dto.Recursion;
import com.rabbit.smart.service.SysRoleService;
import com.rabbit.smart.util.param.Validator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 角色管理
 */
@Api(value = "role", tags = "角色管理")
@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysRoleMapper roleMapper;

    //region 增删改查
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Void> add(SysRole role) {
        Validator.checkNotNull(role.getId(), "角色编号");
        Validator.checkNotNull(role.getParentId(), "父角色编号");
        Validator.checkNotNull(role.getName(), "角色名称");
        roleMapper.insertSelective(role);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ResponseEntity<Void> delete(@RequestParam(value = "id") int id) {
        Validator.checkNotNull(id, "角色编号");
        roleMapper.deleteByPrimaryKey(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity<Void> update(SysRole role) {
        Validator.checkNotNull(role.getId(), "角色编号");
        roleMapper.updateByPrimaryKeySelective(role);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "get", method = RequestMethod.POST)
    public ResponseEntity<SysRole> get(Integer id) {
        return null;
    }

    @RequestMapping(value = "query/tree", method = RequestMethod.POST)
    public ResponseEntity<Recursion<SysRole>> query_tree() {
        Recursion<SysRole> tree = roleService.queryTree();
        return new ResponseEntity(tree, HttpStatus.OK);
    }

    @RequestMapping(value = "query", method = RequestMethod.POST)
    public ResponseEntity<List<SysRole>> query() {
        List<SysRole> roles = roleService.query();
        return new ResponseEntity(roles, HttpStatus.OK);
    }
    //endregion


}