package com.rabbit.smart.config;

import com.rabbit.smart.dao.diy.mapper.DiySysUserMapper;
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
import java.util.Map;

@Configuration
public class ShiroConfiguration {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


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
        shiroFilterFactoryBean.setLoginUrl("/account/unauthorized");//登录页
        //shiroFilterFactoryBean.setSuccessUrl("/index");//首页
        //shiroFilterFactoryBean.setUnauthorizedUrl("/error");//错误页面，认证不通过跳转
        Map<String, String> map = new HashMap<String, String>();
        //图标
        map.put("/favicon.ico", "anon");
        //账号
        map.put("/account/**", "anon");
        //swagger
        map.put("/swagger-ui.html", "anon");
        map.put("/v2/api-docs", "anon");
        map.put("/swagger-resources/**", "anon");
        map.put("/null/swagger-resources/**", "anon");
        map.put("/webjars/springfox-swagger-ui/**", "anon");
        //TODO 读入请求权限（配置较多时失效）
//        List<SysPermission> permissions = diySysUserMapper.queryRequestPermission(null);
//        for (SysPermission permission : permissions) {
//            String url = permission.getUrl();
//            String perm = String.format("perms[%s]", permission.getCode());
//            map.put(url, perm);
//            logger.info("权限控制{},{}", url, perm);
//        }
        //需登陆
//        map.put("/**", "authc");

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

