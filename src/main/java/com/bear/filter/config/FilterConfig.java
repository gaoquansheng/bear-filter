package com.bear.filter.config;

import com.bear.filter.filter.MyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FilterConfig {

    @Autowired
    public MyFilter myFilter;

    @Bean
    public FilterRegistrationBean<MyFilter> filterRegistrationBean() {
        FilterRegistrationBean<MyFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(myFilter);
        filterRegistrationBean.setUrlPatterns(List.of("/*"));
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
