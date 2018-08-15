package com.rabbit.smart.controller;

import com.rabbit.smart.Consts;
import com.rabbit.smart.dao.diy.entity.DiySysUser;
import com.rabbit.smart.dao.entity.SysLoginLog;
import com.rabbit.smart.dao.mapper.SysLoginLogMapper;
import com.rabbit.smart.service.RedisService;
import com.rabbit.smart.service.SysUserService;
import com.rabbit.smart.shiro.util.PasswordHelper;
import com.rabbit.smart.util.CaptchaUtil;
import com.rabbit.smart.util.IPUtil;
import com.rabbit.smart.util.param.Validator;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Api(value = "account", tags = "账号管理")
@RestController
@RequestMapping("account")
public class AccountController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysLoginLogMapper loginLogMapper;
    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "unauthorized", method = RequestMethod.GET)
    public ResponseEntity<String> unauthorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    /**
     * 登录
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<String> login(String account, String password, String code, HttpServletRequest request) {
        //TODO 图形验证码
        //TODO Https
        //验证
        Validator.checkNotNull(account, "账号");
        Validator.checkNotNull(password, "密码");
        Validator.checkNotNull(code, "验证码");

        String ip = IPUtil.getClientIP(request);
        Subject subject = SecurityUtils.getSubject();

        //失败次数校验
        if (redisService.getLoginFaildLimit(account) >= 5) {
            addLoginLog(ip, true, "已连续5次登录失败，锁定30分钟", account);
            return new ResponseEntity("已连续5次登录失败，锁定30分钟", HttpStatus.OK);
        }

        //验证码校验
        if (!code.equals(subject.getSession().getAttribute(Consts.SESSION_CAPTCHA))) {
            return login_fail(ip, "验证码错误", account);
        }

        //获取用户信息
        DiySysUser sysUser = userService.getDiyByAccount(account);
        if (sysUser == null) {
            return login_fail(ip, "账号不存在", account);
        }

        try {
            //登录
            UsernamePasswordToken token = new UsernamePasswordToken(account, PasswordHelper.encryptPassword(password, sysUser.getSalt()), false);
            subject.login(token);
            //Session中保存用户基本信息
            subject.getSession().setAttribute(Consts.SESSION_USER, sysUser);
        } catch (IncorrectCredentialsException ex) {
            return login_fail(ip, "密码错误", account);
        } catch (UnknownAccountException ex) {
            return login_fail(ip, "账号不存在", account);
        } catch (DisabledAccountException ex) {
            return login_fail(ip, "账号不可用", account);
        }
//        catch (ExcessiveAttemptsException ex) {
//            return login_fail(ip, "连续5次登录失败", account);
//        }

        //记录登录成功日志
        redisService.deleteLoginFaildLimit(account);
        addLoginLog(ip, true, "成功", account);
        return new ResponseEntity("成功", HttpStatus.OK);
    }

    private ResponseEntity<String> login_fail(String ip, String reason, String account) {
        redisService.addLoginFailLimit(account);
        addLoginLog(ip, true, reason, account);
        return new ResponseEntity(reason, HttpStatus.OK);
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

    @RequestMapping(value = "captcha.png", method = RequestMethod.GET)
    public ResponseEntity<?> captcha() throws Exception {
        String code = CaptchaUtil.randomStr(4);
        Subject subject = SecurityUtils.getSubject();
        logger.info("验证码：{}", code);
        subject.getSession().setAttribute(Consts.SESSION_CAPTCHA, code);
        return ResponseEntity.ok(CaptchaUtil.creatImage(200, 70, code, 20));
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
