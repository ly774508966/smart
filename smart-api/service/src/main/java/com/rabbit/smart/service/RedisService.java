package com.rabbit.smart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    //region 登录失败次数处理
    private final String KEY_LOGIN_FAIL_LIMIT = "login_fail_limit:%s";

    public void deleteLoginFaildLimit(String account) {
        String key = String.format(KEY_LOGIN_FAIL_LIMIT, account);
        redisTemplate.delete(key);
    }

    public int getLoginFaildLimit(String account) {
        String key = String.format(KEY_LOGIN_FAIL_LIMIT, account);
        Object obj = redisTemplate.opsForValue().get(key);
        if (obj == null)
            return 0;
        return Integer.parseInt(obj.toString());
    }

    public int addLoginFailLimit(String account) {
        String key = String.format(KEY_LOGIN_FAIL_LIMIT, account);
        Long newCount = redisTemplate.opsForValue().increment(key, 1);
        redisTemplate.expire(key, 30, TimeUnit.MINUTES);
        return newCount.intValue();
    }
    //endregion

}
