package com.rabbit.smart.task;

import com.rabbit.smart.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

//@Service
public class TestJob {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(fixedRate = 1000)
    public void execute() {
        logger.info("系统时间：{}", DateUtil.getString("yyyy-MM-dd HH:mm:ss"));
    }
}