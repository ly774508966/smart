package com.rabbit.smart.server;

import com.rabbit.smart.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuzhang on 2018/5/15.
 */
@Service
public class UserService {
    private static Map<String, User> users = new HashMap();

    static {
        users.put("张三", new User("张三", "男", 20));
        users.put("张三", new User("小红", "女", 20));
    }

    @Cacheable(value = "user")
    public User directGetUser(String name, int age) {
        System.out.println("directGetUser:" + name + age);
        return users.get(name);
    }

    @Cacheable(value = "user")
    public User directPostUser(String name, int age) {
        System.out.println("directPostUser:" + name+ age);
        return users.get(name);
    }

    public User redirectGetUser(String name) {
        return directGetUser(name, 20);
    }
}
