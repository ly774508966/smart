package com.rabbit.smart.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by xuzhang on 2018/5/21.
 */
@Service
public class PrintJob {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private int i = 0;

    @Scheduled(fixedRate = 1000)
//    @Scheduled(cron="*/10 * * * * ?")
    public void execute() {
        i++;
        logger.info("PrintJob :{}", i);
    }
}
