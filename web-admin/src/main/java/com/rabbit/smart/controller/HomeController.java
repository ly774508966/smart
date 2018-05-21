package com.rabbit.smart.controller;

import com.alibaba.fastjson.JSONObject;
import com.rabbit.smart.dao.db1.entity.FrmCodeExample;
import com.rabbit.smart.dao.db1.mapper.FrmCodeMapper;
import com.rabbit.smart.dao.db2.entity.XhxtCodeExample;
import com.rabbit.smart.dao.db2.mapper.XhxtCodeMapper;
import com.rabbit.smart.frame.protocol.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private XhxtCodeMapper xhxtCodeMapper;

    @Autowired
    private FrmCodeMapper frmCodeMapper;

    @RequestMapping("index")
    public String index() {
        String[] array = {"1"};
        logger.info(array[2]);
        return "index";
    }

    @RequestMapping("cache")
    @ResponseBody
    @Cacheable(value = "cache")
    public Response<String> cache() {
        logger.info("cache");
        return new Response<>("cache");
    }

    @RequestMapping("two")
    @ResponseBody
    public Response<JSONObject> two() {
        int db_1_code_count = frmCodeMapper.countByExample(new FrmCodeExample());
        int db_2_code_count = xhxtCodeMapper.countByExample(new XhxtCodeExample());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("db_1_code_count", db_1_code_count);
        jsonObject.put("db_2_code_count", db_2_code_count);
        return new Response<>(jsonObject);
    }
}
