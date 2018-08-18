package com.rabbit.smart.controller;

import com.github.pagehelper.PageInfo;
import com.rabbit.smart.dao.diy.entity.DiySysUser;
import com.rabbit.smart.dao.diy.mapper.DiySysUserMapper;
import com.rabbit.smart.dao.entity.SysUser;
import com.rabbit.smart.dao.entity.SysUserExample;
import com.rabbit.smart.dao.mapper.SysUserMapper;
import com.rabbit.smart.dto.in.UserAddDto;
import com.rabbit.smart.dto.in.UserModifyDto;
import com.rabbit.smart.dto.in.UserQueryDto;
import com.rabbit.smart.service.SysUserService;
import com.rabbit.smart.shiro.util.PasswordHelper;
import com.rabbit.smart.util.param.Validator;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
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
    public ResponseEntity<Void> add(UserAddDto params) {
        //检查
        Validator.checkNotNull(params.getAccount(), "用户账号");
        Validator.checkNotNull(params.getPassword(), "用户密码");
        Validator.checkNotNull(params.getName(), "用户姓名");
        Validator.checkNotNull(params.getRoleId(), "用户角色");
        Validator.checkNotNull(params.getDeptId(), "所属部门");
        //对象
        SysUser user = new SysUser();
        user.setAccount(params.getAccount());
        user.setName(params.getName());
        user.setRoleId(params.getRoleId());
        user.setDepartmentId(params.getDeptId());
        user.setCreatetime(new Date());
        user.setStatus(SysUserService.STATUS_USE);
        user.setSalt(PasswordHelper.generateSalt());
        user.setPassword(PasswordHelper.encryptPassword(params.getPassword(), user.getSalt()));
        //插入
        sysUserMapper.insertSelective(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public ResponseEntity<Void> remove(String account) {
        //逻辑删除
        Validator.checkNotNull(account, "用户账号");
        sysUserService.updateUserStatus(account, SysUserService.STATUS_DEL);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public ResponseEntity<Void> modify(UserModifyDto params) {
        //检查
        Validator.checkNotNull(params.getAccount(), "用户账号");
        Validator.checkNotNull(params.getName(), "用户姓名");
        Validator.checkNotNull(params.getRoleId(), "用户角色");
        Validator.checkNotNull(params.getDeptId(), "所属部门");
        Validator.checkNotNull(params.getStatus(), "用户状态");
        //条件
        SysUserExample example = new SysUserExample();
        example.createCriteria().andAccountEqualTo(params.getAccount());
        //对象
        SysUser user = new SysUser();
        user.setName(params.getName());
        user.setRoleId(params.getRoleId());
        user.setDepartmentId(params.getDeptId());
        user.setStatus(params.getStatus());
        if (StringUtils.isNotEmpty(params.getPassword())) {
            user.setSalt(PasswordHelper.generateSalt());
            user.setPassword(PasswordHelper.encryptPassword(params.getPassword(), user.getSalt()));
        }
        //更新
        sysUserMapper.updateByExampleSelective(user, example);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "query", method = RequestMethod.POST)
    public ResponseEntity<PageInfo<DiySysUser>> query(UserQueryDto params) {
        Validator.checkNotNull(params.getPageIndex(), "页码");
        Validator.checkNotNull(params.getPageSize(), "页大小");
        PageInfo<DiySysUser> users = sysUserService.querySysUser(params);
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @RequestMapping(value = "get", method = RequestMethod.POST)
    public ResponseEntity<DiySysUser> get(String account) {
        Validator.checkNotNull(account, "账号");
        DiySysUser user = diySysUserMapper.getDiyUserByAccount(account);
        user.setSalt(null);
        user.setPassword(null);
        return new ResponseEntity(user, HttpStatus.OK);
    }
    //endregion
}
