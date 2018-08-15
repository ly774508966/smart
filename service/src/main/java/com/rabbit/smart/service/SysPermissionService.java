package com.rabbit.smart.service;

import com.rabbit.smart.dao.diy.mapper.DiySysUserMapper;
import com.rabbit.smart.dao.entity.SysPermission;
import com.rabbit.smart.dao.entity.SysPermissionExample;
import com.rabbit.smart.dao.mapper.SysPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysPermissionService {
    @Autowired
    private SysPermissionMapper permissionMapper;

    @Autowired
    private DiySysUserMapper diySysUserMapper;

    public List<SysPermission> selectByRoleId(Integer roleId) {
        return diySysUserMapper.selectPermissionByRoleId(roleId);
    }


    public Map<String, String> queryUrlNameMap() {
        Map<String, String> maps = new HashMap<>();
        SysPermissionExample example = new SysPermissionExample();
        example.createCriteria().andIsRequestEqualTo(true);
        List<SysPermission> permissions = permissionMapper.selectByExample(example);
        if (permissions != null) {
            for (SysPermission permission : permissions) {
                maps.put(permission.getUrl(), permission.getName());
            }
        }
        return maps;
    }

}
