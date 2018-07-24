package com.rabbit.smart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
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
@MapperScan("com.rabbit.smart.dao")//mybatis包扫描
@EnableRedisHttpSession//使用Redis存储Session
public class Application implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}
