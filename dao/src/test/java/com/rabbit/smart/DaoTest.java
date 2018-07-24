package com.rabbit.smart;

import com.rabbit.smart.dao.entity.SysMenu;
import com.rabbit.smart.dao.mapper.SysMenuMapper;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xuzhang on 2018/7/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysMenuMapper menuMapper;

    @Test
    public void menuTest() {
        SysMenu menu = menuMapper.selectByPrimaryKey(1L);
        logger.info(JSONObject.toJSONString(menu));
    }
}