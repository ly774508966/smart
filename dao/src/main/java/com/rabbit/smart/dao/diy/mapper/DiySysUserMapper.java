package com.rabbit.smart.dao.diy.mapper;

import com.rabbit.smart.dao.diy.entity.DiySysUser;
import com.rabbit.smart.dao.entity.SysPermission;

import java.util.List;

public interface DiySysUserMapper {
    //查询用户
    DiySysUser selectDiyUserByAccount(String account);

    //查询角色权限
    List<SysPermission> selectPermissionByRoleId(Integer roleId);
}
