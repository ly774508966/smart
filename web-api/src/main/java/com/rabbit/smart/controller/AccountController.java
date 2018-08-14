package com.rabbit.smart.controller;

import com.rabbit.smart.Consts;
import com.rabbit.smart.dao.diy.entity.DiySysUser;
import com.rabbit.smart.service.SysUserService;
import com.rabbit.smart.shiro.util.PasswordHelper;
import com.rabbit.smart.util.param.Validator;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
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
    private SysUserService userService;

    /**
     * 登录
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<String> login(String account, String password) {
        //TODO 图形验证码
        //TODO Https
        Validator.checkNotNull(account, "账号");
        Validator.checkNotNull(password, "密码");

        DiySysUser sysUser = userService.getDiyByAccount(account);
        if (sysUser == null) {
            return new ResponseEntity("账号不存在", HttpStatus.OK);
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(account, PasswordHelper.encryptPassword(password, sysUser.getSalt()), true);
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException ex) {
            return new ResponseEntity("密码错误", HttpStatus.OK);
        } catch (UnknownAccountException ex) {
            return new ResponseEntity("账号不存在", HttpStatus.OK);
        } catch (DisabledAccountException ex) {
            return new ResponseEntity("账号不可用", HttpStatus.OK);
        } catch (ExcessiveAttemptsException ex) {
            return new ResponseEntity("账号被冻结", HttpStatus.OK);
        }

        //Session中保存用户、部门、角色、权限信息
        subject.getSession().setAttribute(Consts.SESSION_USER, sysUser);

        //TODO 记录登录日志
        return new ResponseEntity("成功", HttpStatus.OK);
    }

    /**
     * 注销
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public ResponseEntity<Void> logout() {
        //TODO 记录登出日志
        SecurityUtils.getSubject().logout();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
