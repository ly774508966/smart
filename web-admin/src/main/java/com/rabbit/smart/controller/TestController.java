package com.rabbit.smart.controller;

import com.rabbit.smart.model.SessionUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by xuzhang on 2018/5/15.
 */
@RestController
@RequestMapping("/test")
public class TestController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/index")
    public SessionUser index(HttpSession session) {
        SessionUser user = createUser();
        if (session.getAttribute("user") == null) {
            session.setAttribute("user", user);
        }
        return user;
    }

    @RequestMapping("/get")
    @Cacheable(value = "get")
    public SessionUser get(String account, String password) {
        logger.info("get:" + account);
        return createUser();
    }

    @RequestMapping("/post")
    @Cacheable(value = "post")
    public SessionUser post(SessionUser user) {
        logger.info("post:" + user.getName());
        return user;
    }

    private SessionUser createUser() {
        SessionUser user = new SessionUser();
        user.setName("张三");
        user.setAccount("zhangsan");
        user.setPassword("123456");
        return user;
    }
}
