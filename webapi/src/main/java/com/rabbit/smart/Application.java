package com.rabbit.smart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by xuzhang on 2018/5/21.
 */
@SpringBootApplication
@EnableScheduling//开启定时任务
@EnableTransactionManagement//开启事务
@EnableRedisHttpSession//持久化Session
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
