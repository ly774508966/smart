package com.rabbit.smart.service;

import com.rabbit.smart.dao.entity.SysRole;
import com.rabbit.smart.dao.entity.SysRoleExample;
import com.rabbit.smart.dao.mapper.SysRoleMapper;
import com.rabbit.smart.dto.KeyValue;
import com.rabbit.smart.dto.Recursion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    //查询某个角色
    public SysRole queryById(Integer id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    //查询树形结构
    public Recursion<SysRole> queryTree() {
        List<SysRole> roles = sysRoleMapper.selectByExample(new SysRoleExample());
        SysRole sysRole = new SysRole();
        sysRole.setId(0);
        Recursion<SysRole> results = new Recursion<>(sysRole);
        return recursion(roles, results);
    }

    //递归调用
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
