package com.rabbit.smart.service;

import com.rabbit.smart.config.RedisCacheConfig;
import com.rabbit.smart.dao.diy.mapper.DiySysUserMapper;
import com.rabbit.smart.dao.entity.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionService {

    @Autowired
    private DiySysUserMapper diySysUserMapper;

    public List<SysPermission> selectPermissionsByRoleId(Integer roleId) {
        return diySysUserMapper.selectPermissionByRoleId(roleId);
    }

}
