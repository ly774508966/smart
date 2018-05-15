package com.rabbit.smart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by xuzhang on 2018/5/15.
 */
@SpringBootApplication
public class SchedulerApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SchedulerApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}
