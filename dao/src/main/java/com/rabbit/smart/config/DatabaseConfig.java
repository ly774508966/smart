package com.rabbit.smart.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 数据库配置
 * Created by xuzhang on 2018/7/24.
 */
@Configuration
@MapperScan("com.rabbit.smart.dao")
public class DatabaseConfig {
}
