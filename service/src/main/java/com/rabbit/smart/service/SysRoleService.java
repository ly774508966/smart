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

    public SysRole queryById(Integer id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    public List<SysRole> queryAll() {
        SysRoleExample example = new SysRoleExample();
        List<SysRole> roles = sysRoleMapper.selectByExample(example);
        return roles;
    }

    public Recursion<SysRole> queryRecursions(int start) {
        List<SysRole> roles = queryAll();
        SysRole sysRole = new SysRole();
        sysRole.setId(start);
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
