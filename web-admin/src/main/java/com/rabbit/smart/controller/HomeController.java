package com.rabbit.smart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xuzhang on 2018/5/21.
 */
@Controller
public class HomeController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("cache")
    @ResponseBody
    @Cacheable(value = "cache")
    public String cache() {
        logger.info("cache");
        return "cache";
    }
}
