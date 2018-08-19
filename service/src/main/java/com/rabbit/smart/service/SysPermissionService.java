package com.rabbit.smart.service;

import com.rabbit.smart.dao.diy.mapper.DiySysUserMapper;
import com.rabbit.smart.dao.entity.SysPermission;
import com.rabbit.smart.dao.entity.SysPermissionExample;
import com.rabbit.smart.dao.mapper.SysPermissionMapper;
import com.rabbit.smart.dto.Recursion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysPermissionService {
    @Autowired
    private DiySysUserMapper diySysUserMapper;
    @Autowired
    private SysPermissionMapper permissionMapper;

    //查询url和name的映射关系（请求）
    public Map<String, String> queryRequestUrlAndName(Boolean isLog) {
        Map<String, String> maps = new HashMap<>();
        List<SysPermission> permissions = diySysUserMapper.queryRequestPermission(isLog);
        if (permissions != null) {
            for (SysPermission permission : permissions) {
                maps.put(permission.getUrl(), permission.getName());
            }
        }
        return maps;
    }

    //查询某个用户的权限（菜单）
    public Recursion<SysPermission> queryMenuTreeByRole(Integer roleId) {
        List<SysPermission> permissions = diySysUserMapper.queryMenuPermissionByRoleId(roleId);
        SysPermission permission = new SysPermission();
        permission.setCode("0");
        Recursion<SysPermission> results = new Recursion<>(permission);
        return recursion(permissions, results);
    }

    //查询所有权限（含菜单、请求）
    public Recursion<SysPermission> queryTree() {
        List<SysPermission> permissions = permissionMapper.selectByExample(new SysPermissionExample());
        SysPermission permission = new SysPermission();
        permission.setCode("0");
        Recursion<SysPermission> results = new Recursion<>(permission);
        return recursion(permissions, results);
    }

    private Recursion<SysPermission> recursion(List<SysPermission> permissions, Recursion<SysPermission> results) {
        List<Recursion<SysPermission>> subs = new ArrayList<>();
        results.setSubs(subs);//子元素
        for (SysPermission role : permissions) {
            if (role.getParentCode().equals(results.getNode().getCode())) {
                Recursion sub = new Recursion<>(role);
                subs.add(sub);
                recursion(permissions, sub);
            }
        }
        return results;
    }

}
