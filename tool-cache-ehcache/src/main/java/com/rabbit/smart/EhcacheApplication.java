package com.rabbit.smart;

import com.rabbit.smart.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by xuzhang on 2018/5/15.
 */
@SpringBootApplication
public class EhcacheApplication implements CommandLineRunner {
    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(EhcacheApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        userService.directGetUser("张三", 20);//缓存未命中
        userService.directGetUser("张三", 21);//缓存未命中
        userService.directGetUser("小红", 20);//缓存未命中

        //间接调用带Cacheable注解的方法，缓存不会命中
        userService.redirectGetUser("张三");//缓存未命中

        userService.directGetUser("张三", 20);//缓存命中
        userService.directGetUser("张三", 21);//缓存命中
        userService.directGetUser("小红", 20);//缓存命中
        userService.directPostUser("张三", 20);//缓存命中

        Thread.sleep(2000);
        userService.directGetUser("张三", 20);//缓存未命中
    }
}
