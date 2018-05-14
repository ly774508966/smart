package com.rabbit.smart.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xuzhang on 2018/5/13.
 */
@RestController
public class HelloWorldController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

    @RequestMapping("/cache")
    @Cacheable(value = "test")
    public JSONObject cache(String value) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", value);
        logger.info("cache {}", value);
        return jsonObject;
    }
}
