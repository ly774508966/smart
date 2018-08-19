package com.rabbit.smart.shiro;

import com.alibaba.fastjson.JSONObject;
import com.rabbit.smart.dao.diy.entity.DiySysUser;
import com.rabbit.smart.dao.diy.mapper.DiySysUserMapper;
import com.rabbit.smart.dao.entity.SysPermission;
import com.rabbit.smart.dao.entity.SysUser;
import com.rabbit.smart.service.SysPermissionService;
import com.rabbit.smart.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MyShiroRealm extends AuthorizingRealm {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 用户对应的角色信息与权限信息都保存在数据库中，通过UserService获取数据
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysPermissionService permissionService;
    @Autowired
    private DiySysUserMapper diySysUserMapper;


    /**
     * 提供用户信息返回权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("Shiro 权限认证");
        String account = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //根据用户名查询当前用户拥有的角色
        Set<String> roleNames = new HashSet<>();
        DiySysUser user = diySysUserMapper.getDiyUserByAccount(account);
        roleNames.add(user.getRoleName());
        authorizationInfo.setRoles(roleNames);

        //根据用户名查询当前用户权限
        List<SysPermission> permissions = diySysUserMapper.queryRequestPermissionByRoleId(user.getRoleId());
        Set<String> permissionCodes = new HashSet<>();
        for (SysPermission permission : permissions) {
            permissionCodes.add(permission.getCode());
        }
        logger.info(JSONObject.toJSONString(permissionCodes));
        authorizationInfo.setStringPermissions(permissionCodes);
        return authorizationInfo;
    }

    /**
     * 提供账户信息返回认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("Shiro 用户认证");
        String account = (String) token.getPrincipal();
        SysUser user = userService.getByAccount(account);
        if (user == null) {//账号不存在
            throw new UnknownAccountException();
        }
        if (user.getStatus().equals(SysUserService.STATUS_NO_USE) || user.getStatus().equals(SysUserService.STATUS_DEL)) {//账号被锁定或者被删除
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getAccount(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        return authenticationInfo;
    }
}