package com.rabbit.smart.service;

import com.rabbit.smart.dao.entity.SysRole;
import com.rabbit.smart.dao.entity.SysRoleExample;
import com.rabbit.smart.dao.entity.SysRolePermission;
import com.rabbit.smart.dao.entity.SysRolePermissionExample;
import com.rabbit.smart.dao.mapper.SysRoleMapper;
import com.rabbit.smart.dao.mapper.SysRolePermissionMapper;
import com.rabbit.smart.dto.Recursion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;


    //查询某个角色
    public SysRole getByID(Integer id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    //查询所有角色
    public List<SysRole> query() {
        SysRoleExample example = new SysRoleExample();
        example.setOrderByClause("id asc");
        List<SysRole> roles = sysRoleMapper.selectByExample(example);
        return roles;
    }

    //查询某个角色的权限
    public List<Integer> getRolePermission(Integer roleId) {
        SysRolePermissionExample example = new SysRolePermissionExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<SysRolePermission> permissions = sysRolePermissionMapper.selectByExample(example);
        List<Integer> results = new ArrayList<>();
        if (permissions != null && permissions.size() > 0) {
            for (SysRolePermission permission : permissions) {
                results.add(permission.getPermissionId());
            }
        }
        return results;
    }

    //查询树形结构
    public Recursion<SysRole> queryTree() {
        List<SysRole> roles = query();
        SysRole sysRole = new SysRole();
        sysRole.setId(0);
        Recursion<SysRole> results = new Recursion<>(sysRole);
        return recursion(roles, results);
    }

    private Recursion<SysRole> recursion(List<SysRole> roles, Recursion<SysRole> results) {
        List<Recursion<SysRole>> subs = new ArrayList<>();
        results.setSubs(subs);//子元素
        for (SysRole role : roles) {
            if (role.getParentId().equals(results.getNode().getId())) {
                Recursion sub = new Recursion<>(role);
                subs.add(sub);
                recursion(roles, sub);
            }
        }
        return results;
    }

}
