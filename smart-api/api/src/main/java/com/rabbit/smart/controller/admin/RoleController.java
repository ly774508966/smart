package com.rabbit.smart.controller.admin;

import com.rabbit.smart.dao.diy.mapper.DiySysUserMapper;
import com.rabbit.smart.dao.entity.SysRole;
import com.rabbit.smart.dao.mapper.SysRoleMapper;
import com.rabbit.smart.dto.in.RoleAddDto;
import com.rabbit.smart.dto.in.RoleModifyDto;
import com.rabbit.smart.service.SysRoleService;
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
    @Autowired
    private DiySysUserMapper diySysUserMapper;

    //region 增删改查
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Void> add(RoleAddDto params) {
        Validator.checkNotNull(params.getName(), "角色名称");
        SysRole role = new SysRole();
        role.setParentId(0);
        role.setName(params.getName());
        role.setDescription(params.getDescription());
        roleMapper.insertSelective(role);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public ResponseEntity<Void> remove(int id) {
        Validator.checkNotNull(id, "角色编号");
        roleMapper.deleteByPrimaryKey(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public ResponseEntity<Void> modify(RoleModifyDto params) {
        Validator.checkNotNull(params.getId(), "角色编号");
        Validator.checkNotNull(params.getName(), "角色名称");
        SysRole role = new SysRole();
        role.setId(params.getId());
        role.setName(params.getName());
        role.setDescription(params.getDescription());
        roleMapper.updateByPrimaryKeySelective(role);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "get/permissions", method = RequestMethod.POST)
    public ResponseEntity<List<Integer>> get_permissions(Integer roleId) {
        Validator.checkNotNull(roleId, "角色编号");
        List<Integer> permissions = roleService.getRolePermission(roleId);
        return new ResponseEntity(permissions, HttpStatus.OK);
    }

    @RequestMapping(value = "modify/permissions", method = RequestMethod.POST)
    public ResponseEntity<Void> modify_permissions(Integer roleId, String permissionIds) {
        Validator.checkNotNull(roleId, "角色编号");
        Validator.checkNotNull(permissionIds, "权限列表");
        String[] permissions = permissionIds.split(",");
        diySysUserMapper.updateRolePermissions(roleId,permissions);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "query", method = RequestMethod.POST)
    public ResponseEntity<List<SysRole>> query() {
        List<SysRole> roles = roleService.query();
        return new ResponseEntity(roles, HttpStatus.OK);
    }
    //endregion
}
