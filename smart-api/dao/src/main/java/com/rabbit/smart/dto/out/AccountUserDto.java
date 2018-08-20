package com.rabbit.smart.dto.out;

import com.rabbit.smart.dao.diy.entity.DiySysUser;
import com.rabbit.smart.dao.entity.SysPermission;
import com.rabbit.smart.dto.Recursion;

public class AccountUserDto {
    public AccountUserDto() {
    }

    public AccountUserDto(DiySysUser user, Recursion<SysPermission> menus) {
        this.user = user;
        this.menus = menus;
    }

    private DiySysUser user;
    private Recursion<SysPermission> menus;

    public DiySysUser getUser() {
        return user;
    }

    public void setUser(DiySysUser user) {
        this.user = user;
    }

    public Recursion<SysPermission> getMenus() {
        return menus;
    }

    public void setMenus(Recursion<SysPermission> menus) {
        this.menus = menus;
    }
}
