package com.rabbit.test;

import com.rabbit.smart.WebMultiApplication;
import com.rabbit.smart.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by xuzhang on 2018/5/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebMultiApplication.class)
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("value", "111");
        stringRedisTemplate.opsForValue().set("value.tmp", "111", 1, TimeUnit.MINUTES);
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("value"));
    }

    @Test
    public void testObj() throws Exception {
        User user = new User("张三", "男", 20);
        redisTemplate.opsForValue().set("user", user);
        redisTemplate.opsForValue().set("user.tmp", user, 1, TimeUnit.MINUTES);
        User object = (User) redisTemplate.opsForValue().get("user");
        Assert.assertEquals(user.getName(), object.getName());
    }
}

