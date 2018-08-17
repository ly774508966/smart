package com.rabbit.smart.dao.diy.mapper;

import com.rabbit.smart.dao.diy.entity.DiySysUser;
import com.rabbit.smart.dao.entity.SysPermission;
import com.rabbit.smart.dao.entity.SysUser;
import com.rabbit.smart.dto.in.UserQueryDto;

import java.util.List;

public interface DiySysUserMapper {
    //region 用户
    //查询用户
    DiySysUser getDiyUserByAccount(String account);

    List<DiySysUser> queryDiySysUser(UserQueryDto params);
    //endregion

    //region 权限
    //查询角色请求权限
    List<SysPermission> queryRequestPermissionByRoleId(Integer roleId);

    //查询请求权限
    List<SysPermission> queryRequestPermission();

    //查询角色菜单权限
    List<SysPermission> queryMenuPermissionByRoleId(Integer roleId);
    //endregion
}
