package com.rabbit.smart.controller;

import com.rabbit.smart.domain.User;
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
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/index")
    public String index(HttpSession session) {
        if (session.getAttribute("user") == null) {
            session.setAttribute("user", new User("张三", "男", 20));
            session.setAttribute("user_id", 1);
        }
        return "Hello World";
    }

    @RequestMapping("/get")
    @Cacheable(value = "get")
    public User get(String name, String sex, int age) {
        logger.info("get:" + name);
        return new User(name, sex, age);
    }

    @RequestMapping("/post")
    @Cacheable(value = "post", key = "#user.name+','+#user.sex+','+#user.age")
    public User post(User user) {
        logger.info("post:" + user.getName());
        return user;
    }
}
