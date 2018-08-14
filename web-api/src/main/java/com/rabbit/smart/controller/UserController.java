package com.rabbit.smart.controller;

import com.rabbit.smart.dao.entity.SysUser;
import com.rabbit.smart.dao.mapper.SysUserMapper;
import com.rabbit.smart.service.SysUserService;
import com.rabbit.smart.shiro.util.PasswordHelper;
import com.rabbit.smart.util.param.Validator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 用户管理
 */
@Api(value = "user", tags = "用户管理")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private SysUserMapper sysUserMapper;

    //region 增删改查
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void add(SysUser user) {
        Validator.checkNotNull(user.getAccount(), "账号");
        Validator.checkNotNull(user.getPassword(), "密码");
        Validator.checkNotNull(user.getName(), "姓名");
        Validator.checkNotNull(user.getRoleId(), "角色编号");
        Validator.checkNotNull(user.getDepartmentId(), "部门编号");
        user.setCreatetime(new Date());
        user.setSalt(PasswordHelper.generateSalt());
        user.setStatus(SysUserService.STATUS_USE);
        user.setPassword(PasswordHelper.encryptPassword(user.getPassword(), user.getSalt()));
        sysUserMapper.insertSelective(user);
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public void remove() {

    }

    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public void modify() {

    }

    @RequestMapping(value = "query", method = RequestMethod.POST)
    public void query() {

    }
    //endregion
}
