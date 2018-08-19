package com.rabbit.smart.service;

import com.rabbit.smart.dao.entity.SysOperationLog;
import com.rabbit.smart.dao.mapper.SysOperationLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class SysOperationLogService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SysOperationLogMapper logMapper;

    private static final ExecutorService service = Executors.newSingleThreadExecutor();

    public void addLog(SysOperationLog log) {
        service.execute(new Runnable() {
            @Override
            public void run() {
                log.setCreatetime(new Date());
                logMapper.insertSelective(log);
            }
        });
    }
}
