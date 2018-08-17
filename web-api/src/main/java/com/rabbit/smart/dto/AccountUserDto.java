package com.rabbit.smart.dto;

import com.rabbit.smart.dao.diy.entity.DiySysUser;
import com.rabbit.smart.dao.entity.SysPermission;

public class AccountUserDto {
    public AccountUserDto() {
    }

    public AccountUserDto(DiySysUser user, Recursion<SysPermission> perms) {
        this.user = user;
        this.perms = perms;
    }

    private DiySysUser user;
    private Recursion<SysPermission> perms;

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
}
