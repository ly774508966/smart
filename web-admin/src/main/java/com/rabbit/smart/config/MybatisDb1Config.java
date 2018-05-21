package com.rabbit.smart.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by xuzhang on 2018/2/7.
 */
@Configuration
@MapperScan(basePackages = {MybatisDb1Config.PACKAGE}, sqlSessionFactoryRef = "sqlSessionFactory1")
public class MybatisDb1Config extends MybatisDbConfig {

    static final String PACKAGE = "com.rabbit.smart.dao.db1";
    static final String MAPPER_LOCATION = "classpath:mapper/db1/*.xml";

    @Value("${spring.datasource.db1.url}")
    private String url;
    @Value("${spring.datasource.db1.username}")
    private String user;
    @Value("${spring.datasource.db1.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;

    @Bean(name = "db1")
    public DataSource dataSource1() {
        return dataSource(driverClass, url, user, password);
    }

    @Bean(name = "sqlSessionFactory1")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("db1") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return sessionFactoryBean.getObject();
    }

    @Bean(name = "db1TransactionManager")
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(dataSource1());
    }

}
