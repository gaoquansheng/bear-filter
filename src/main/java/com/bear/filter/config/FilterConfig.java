package com.bear.filter.config;

import com.bear.filter.filter.TestFilter;
import com.bear.filter.filter.TestFilter2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Autowired
    private TestFilter testFilter;

    @Autowired
    private TestFilter2 testFilter2;

    @Bean
    public FilterRegistrationBean<TestFilter> filterRegistrationBean() {
        FilterRegistrationBean<TestFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(testFilter);
        registration.setOrder(3);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<TestFilter2> filterRegistrationBean2() {
        FilterRegistrationBean<TestFilter2> registration = new FilterRegistrationBean<>();
        registration.setFilter(testFilter2);
        registration.setOrder(2);
        return registration;
    }
}
