package com.rabbit.smart.controller;

import com.rabbit.smart.Consts;
import com.rabbit.smart.dao.diy.entity.DiySysUser;
import com.rabbit.smart.service.DiySysUserService;
import com.rabbit.smart.service.SysUserService;
import com.rabbit.smart.util.param.Validator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Api(value = "account", tags = "账号管理")
@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private DiySysUserService diySysUserService;

    /**
     * 登录
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<String> login(String account, String password, HttpSession session) {
        //TODO 图形验证码
        Validator.checkNotNull(account, "账号");
        Validator.checkNotNull(password, "密码");
        DiySysUser sysUser = diySysUserService.getByAccount(account);
        if (sysUser == null) {
            return new ResponseEntity("账号不存在", HttpStatus.OK);
        }
        if (sysUser.getStatus().equals(SysUserService.STATUS_NO_USE)) {
            return new ResponseEntity("账号被冻结", HttpStatus.OK);
        }
        if (sysUser.getStatus().equals(SysUserService.STATUS_DEL)) {
            return new ResponseEntity("账号已删除", HttpStatus.OK);
        }
        if (!sysUser.getPassword().equals(password)) {
            return new ResponseEntity("密码错误", HttpStatus.OK);
        }

        //TODO Session保存用户、部门、角色、权限信息
        session.setAttribute(Consts.SESSION_USER, sysUser);

        //TODO 记录登录日志
        return new ResponseEntity("成功", HttpStatus.OK);
    }

    /**
     * 注销
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ResponseEntity<Void> logout(HttpSession session) {
        //TODO 记录登出日志
        session.invalidate();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 登录注销日志查看
     */
    @RequestMapping(value = "log", method = RequestMethod.GET)
    public ResponseEntity<Void> log() {
        //TODO 分页查看登录日志
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
