package com.rabbit.smart.config;

import com.rabbit.smart.filter.LogFilter;
import com.rabbit.smart.filter.CORSFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xuzhang on 2018/5/13.
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LogFilter());
        registration.addUrlPatterns("/*");
        registration.setName("logFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean sessionFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CORSFilter());
        registration.addUrlPatterns("/*");
        registration.setName("CORSFilter");
        registration.setOrder(2);
        return registration;
    }
}
