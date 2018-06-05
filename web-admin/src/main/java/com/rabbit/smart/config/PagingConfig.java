package com.rabbit.smart.config;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class PagingConfig {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public PageHelper pageHelper() {
        logger.info("------{} 初始化中------,", "PageHelper");
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        p.setProperty("pageSizeZero", "true"); //通过设置pageSize=0或者RowBounds.limit = 0就会查询出全部的结果。
        pageHelper.setProperties(p);
        return pageHelper;
    }

}
