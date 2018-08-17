package com.rabbit.smart.dto.out;

import com.rabbit.smart.dao.diy.entity.DiySysUser;
import com.rabbit.smart.dao.entity.SysPermission;
import com.rabbit.smart.dao.entity.SysRole;
import com.rabbit.smart.dto.Recursion;

import java.util.List;

public class AccountUserDto {
    public AccountUserDto() {
    }

    public AccountUserDto(DiySysUser user, Recursion<SysPermission> perms, List<SysRole> roles) {
        this.user = user;
        this.perms = perms;
        this.roles = roles;
    }

    private DiySysUser user;
    private Recursion<SysPermission> perms;
    private List<SysRole> roles;

    public DiySysUser getUser() {
        return user;
    }

    public void setUser(DiySysUser user) {
        this.user = user;
    }

    public Recursion<SysPermission> getPerms() {
        return perms;
    }

    public void setPerms(Recursion<SysPermission> perms) {
        this.perms = perms;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
