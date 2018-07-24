package com.rabbit.smart.config;

import com.rabbit.smart.filter.ResponseHeaderFilter;
import com.rabbit.smart.filter.RequestXssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * Created by xuzhang on 2018/5/13.
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean xssFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RequestXssFilter());
        registration.addUrlPatterns("/*");
        registration.setName(RequestXssFilter.class.getSimpleName());
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
