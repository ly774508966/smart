package com.rabbit.smart.config;

import com.rabbit.smart.dao.diy.mapper.DiySysUserMapper;
import com.rabbit.smart.dao.entity.SysPermission;
import com.rabbit.smart.dao.entity.SysPermissionExample;
import com.rabbit.smart.dao.mapper.SysPermissionMapper;
import com.rabbit.smart.service.SysPermissionService;
import com.rabbit.smart.shiro.MyShiroRealm;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfiguration {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DiySysUserMapper diySysUserMapper;

    //将自己的验证方式加入容器
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        securityManager.setCacheManager(new MemoryConstrainedCacheManager());//TODO 要不要放入redis
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new HashMap<String, String>();
        //图标
        map.put("/favicon.ico", "anon");
        //账号
        map.put("/account/**", "anon");
        //匿名
        map.put("/swagger-ui.html", "anon");
        map.put("/v2/api-docs", "anon");
        map.put("/swagger-resources/**", "anon");
        map.put("/null/swagger-resources/**", "anon");
        map.put("/webjars/springfox-swagger-ui/**", "anon");
        //禁用
        map.put("/**", "authc");
        //读入请求权限
        List<SysPermission> permissions = diySysUserMapper.selectRequestPermission();
        for (SysPermission permission : permissions) {
            map.put(permission.getUrl(), String.format("perms[%s]", permission.getCode()));
        }
        //登录
        shiroFilterFactoryBean.setLoginUrl("/account/unauthorized");
        //首页
        //shiroFilterFactoryBean.setSuccessUrl("/index");
        //错误页面，认证不通过跳转
        //shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


}

