package com.rabbit.smart.dao.diy.entity;

import com.rabbit.smart.dao.entity.SysUser;

import java.io.Serializable;

public class DiySysUser extends SysUser implements Serializable{
    public DiySysUser() {
    }

    private String departmentName;
    private String roleName;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
