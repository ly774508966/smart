package com.rabbit.smart.config;

import com.rabbit.smart.filter.ResponseHeaderFilter;
import com.rabbit.smart.filter.LogFilter;
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
        registration.setName(LogFilter.class.getSimpleName());
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean sessionFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ResponseHeaderFilter());
        registration.addUrlPatterns("/*");
        registration.setName(ResponseHeaderFilter.class.getSimpleName());
        registration.setOrder(2);
        return registration;
    }
}
