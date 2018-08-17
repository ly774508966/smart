package com.rabbit.smart.controller;

import com.github.pagehelper.PageInfo;
import com.rabbit.smart.dao.diy.entity.DiySysUser;
import com.rabbit.smart.dao.diy.mapper.DiySysUserMapper;
import com.rabbit.smart.dao.entity.SysUser;
import com.rabbit.smart.dao.mapper.SysUserMapper;
import com.rabbit.smart.dto.in.UserQueryDto;
import com.rabbit.smart.service.SysUserService;
import com.rabbit.smart.shiro.util.PasswordHelper;
import com.rabbit.smart.util.param.Validator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private DiySysUserMapper diySysUserMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserService sysUserService;

    //region 增删改查
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Void> add(SysUser user) {
        //TODO 头像
        Validator.checkNotNull(user.getAccount(), "账号");
        Validator.checkNotNull(user.getPassword(), "密码");
        Validator.checkNotNull(user.getName(), "姓名");
        Validator.checkNotNull(user.getRoleId(), "角色编号");
        Validator.checkNotNull(user.getDepartmentId(), "部门编号");
        user.setId(null);
        user.setCreatetime(new Date());
        user.setSalt(PasswordHelper.generateSalt());
        user.setStatus(SysUserService.STATUS_USE);
        user.setPassword(PasswordHelper.encryptPassword(user.getPassword(), user.getSalt()));
        sysUserMapper.insertSelective(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public ResponseEntity<Void> remove(int userId) {
        //逻辑删除
        Validator.checkNotNull(userId, "用户编号");
        sysUserService.updateUserStatus(userId, SysUserService.STATUS_DEL);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public ResponseEntity<Void> modify(SysUser user) {
        //TODO 头像
        Validator.checkNotNull(user.getId(), "用户编号");
        user.setPassword(null);
        user.setSalt(null);
        user.setCreatetime(null);
        sysUserMapper.updateByPrimaryKeySelective(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "query", method = RequestMethod.POST)
    public ResponseEntity<PageInfo<DiySysUser>> query(UserQueryDto params) {
        PageInfo<DiySysUser> users = sysUserService.querySysUser(params);
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public ResponseEntity<DiySysUser> get(String account) {
        Validator.checkNotNull(account, "账号");
        DiySysUser user = diySysUserMapper.getDiyUserByAccount(account);
        user.setSalt(null);
        user.setPassword(null);
        return new ResponseEntity(user, HttpStatus.OK);
    }
    //endregion
}
