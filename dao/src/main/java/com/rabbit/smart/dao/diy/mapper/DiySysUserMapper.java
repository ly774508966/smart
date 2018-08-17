package com.rabbit.smart.dao.diy.mapper;

import com.rabbit.smart.dao.diy.entity.DiySysUser;
import com.rabbit.smart.dao.entity.SysPermission;

import java.util.List;

public interface DiySysUserMapper {
    //region 用户
    //查询用户
    DiySysUser selectDiyUserByAccount(String account);
    //endregion

    //region 权限
    //查询角色请求权限
    List<SysPermission> selectRequestPermissionByRoleId(Integer roleId);

    //查询请求权限
    List<SysPermission> selectRequestPermission();

    //查询角色菜单权限
    List<SysPermission> selectMenuPermissionByRoleId(Integer roleId);
    //endregion
}
