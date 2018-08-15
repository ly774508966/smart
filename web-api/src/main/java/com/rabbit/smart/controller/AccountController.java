package com.rabbit.smart.controller;

import com.rabbit.smart.Consts;
import com.rabbit.smart.dao.diy.entity.DiySysUser;
import com.rabbit.smart.dao.entity.SysLoginLog;
import com.rabbit.smart.dao.mapper.SysLoginLogMapper;
import com.rabbit.smart.service.SysUserService;
import com.rabbit.smart.shiro.util.PasswordHelper;
import com.rabbit.smart.util.IPUtil;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Api(value = "account", tags = "账号管理")
@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private SysUserService userService;
    @Autowired
    private SysLoginLogMapper loginLogMapper;

    @RequestMapping(value = "unauthorized", method = RequestMethod.GET)
    public ResponseEntity<String> unauthorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    /**
     * 登录
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<String> login(String account, String password, HttpServletRequest request) {
        //TODO 图形验证码
        //TODO Https
        //验证
        Validator.checkNotNull(account, "账号");
        Validator.checkNotNull(password, "密码");

        String ip = IPUtil.getClientIP(request);

        //获取用户信息
        DiySysUser sysUser = userService.getDiyByAccount(account);
        if (sysUser == null) {
            addLoginLog(ip, true, "账号不存在", account);
            return new ResponseEntity("账号不存在", HttpStatus.OK);
        }

        try {
            //登录
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(account, PasswordHelper.encryptPassword(password, sysUser.getSalt()), true);
            subject.login(token);

            //Session中保存用户基本信息
            subject.getSession().setAttribute(Consts.SESSION_USER, sysUser);
        } catch (IncorrectCredentialsException ex) {
            addLoginLog(ip, true, "密码错误", account);
            return new ResponseEntity("密码错误", HttpStatus.OK);
        } catch (UnknownAccountException ex) {
            addLoginLog(ip, true, "账号不存在", account);
            return new ResponseEntity("账号不存在", HttpStatus.OK);
        } catch (DisabledAccountException ex) {
            addLoginLog(ip, true, "账号不可用", account);
            return new ResponseEntity("账号不可用", HttpStatus.OK);
        } catch (ExcessiveAttemptsException ex) {
            addLoginLog(ip, true, "账号被冻结", account);
            return new ResponseEntity("账号被冻结", HttpStatus.OK);
        }

        //记录登录成功日志
        addLoginLog(ip, true, "成功", account);

        return new ResponseEntity("成功", HttpStatus.OK);
    }

    /**
     * 注销
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();

        //已注销则不再注销
        if (!subject.isAuthenticated())
            return ResponseEntity.status(HttpStatus.OK).build();

        //记录注销日志
        addLoginLog(IPUtil.getClientIP(request), false, "成功", subject.getPrincipal().toString());

        //注销
        subject.logout();

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //记录日志
    private void addLoginLog(String ip, Boolean isLogin, String result, String account) {
        SysLoginLog log = new SysLoginLog();
        log.setIsLogin(isLogin);
        log.setCreatetime(new Date());
        log.setIp(ip);
        log.setResult(result);
        log.setAccount(account);
        loginLogMapper.insertSelective(log);
    }
}
