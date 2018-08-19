package com.rabbit.smart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Session持久化配置
 * Created by xuzhang on 2018/7/24.
 */
@Configuration
@EnableRedisHttpSession
public class SessionConfig {
}
