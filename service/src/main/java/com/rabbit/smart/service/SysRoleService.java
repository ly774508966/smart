package com.rabbit.smart.service;

import com.rabbit.smart.dao.entity.SysRole;
import com.rabbit.smart.dao.entity.SysRoleExample;
import com.rabbit.smart.dao.mapper.SysRoleMapper;
import com.rabbit.smart.dto.Recursion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

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
