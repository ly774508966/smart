package com.rabbit.smart.controller;

import com.rabbit.smart.config.RedisCacheConfig;
import com.rabbit.smart.model.SessionUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 测试类
 */
//@RestController
//@RequestMapping("/test")
public class TestController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/index")
    public SessionUser index(HttpSession session) {
        SessionUser user = createUser();
        if (session.getAttribute("user") == null) {
            session.setAttribute("user", user);
        }
        return user;
    }

    @RequestMapping("/get")
    @Cacheable(value = RedisCacheConfig.CACHE_MINUTE, key = "#account")
    public SessionUser get(String account) {
        logger.info("get:" + account);
        return createUser();
    }

    @RequestMapping("/post")
    @Cacheable(value = RedisCacheConfig.CACHE_MINUTE, key = "#user.account")
    public SessionUser post(SessionUser user) {
        logger.info("post:" + user.getName());
        return user;
    }

    @RequestMapping("/redis")
    public String getString() {
        redisTemplate.opsForValue().set("1", "2");
        redisTemplate.opsForValue().set("2", createUser());
        return "";
    }

    private SessionUser createUser() {
        SessionUser user = new SessionUser();
        user.setName("张三");
        user.setAccount("zhangsan");
        user.setPassword("123456");
        return user;
    }
}
