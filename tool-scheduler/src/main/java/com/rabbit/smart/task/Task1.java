package com.rabbit.smart.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xuzhang on 2018/5/13.
 */
@Component
public class Task1 {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron="*/10 * * * * ?")
    public void reportCurrentTime() {
        System.out.println("任务一：现在时间" + dateFormat.format(new Date()));
    }
}
