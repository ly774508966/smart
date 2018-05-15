package com.rabbit.smart.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * Created by xuzhang on 2018/5/15.
 */
@Configuration
@EnableCaching
public class CacheConfig {
    /**
     * 一个方法一个缓存
     * @return
     */
//    @Bean
//    public KeyGenerator keyGenerator() {
//        return new KeyGenerator() {
//            @Override
//            public Object generate(Object target, Method method, Object... params) {
//                StringBuilder sb = new StringBuilder();
//                sb.append(target.getClass().getName());
//                sb.append("." + method.getName());
//                for (Object obj : params) {
//                    sb.append("." + obj.toString());
//                }
//                return sb.toString();
//            }
//        };
//    }
}
