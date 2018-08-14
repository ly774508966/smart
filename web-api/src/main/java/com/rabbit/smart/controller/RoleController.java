package com.rabbit.smart.controller;

import com.github.pagehelper.PageInfo;
import com.rabbit.smart.dao.entity.SysRole;
import com.rabbit.smart.dao.mapper.SysRoleMapper;
import com.rabbit.smart.dto.Recursion;
import com.rabbit.smart.service.SysRoleService;
import com.rabbit.smart.util.param.Validator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 角色管理
 */
@Api(value = "role", tags = "角色管理")
@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    //region 增删改查
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> add(SysRole role) {
        Validator.checkNotNull(role.getId(), "角色编号");
        Validator.checkNotNull(role.getParentId(), "父角色编号");
        Validator.checkNotNull(role.getName(), "角色名称");
        sysRoleMapper.insertSelective(role);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@RequestParam(value = "id") int id) {
        Validator.checkNotNull(id, "角色编号");
        sysRoleMapper.deleteByPrimaryKey(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> update(SysRole role) {
        Validator.checkNotNull(role.getId(), "角色编号");
        sysRoleMapper.updateByPrimaryKeySelective(role);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public SysRole query(@PathVariable("id") Integer id) {
        return sysRoleService.queryById(id);
    }


    @RequestMapping(value = "tree", method = RequestMethod.GET)
    public Recursion<SysRole> query_tree() {
        return sysRoleService.queryRecursions(0);
    }
    //endregion
}
