package com.rabbit.smart.config;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

/**
 * Created by xuzhang on 2018/5/21.
 */
public class MybatisDbConfig {

    protected DataSource dataSource(String driverClass, String url, String user, String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }
}
