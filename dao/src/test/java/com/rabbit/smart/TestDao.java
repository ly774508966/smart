package com.rabbit.smart;

import com.alibaba.fastjson.JSONObject;
import com.rabbit.smart.dao.entity.SysUser;
import com.rabbit.smart.dao.mapper.SysUserMapper;
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
public class TestDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void menuTest() {
        SysUser user = sysUserMapper.selectByPrimaryKey(1);
        logger.info(JSONObject.toJSONString(user));
    }
}