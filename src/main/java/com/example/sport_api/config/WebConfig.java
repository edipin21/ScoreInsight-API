package com.example.sport_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.sport_api.filters.ApiKeyValidationFilter;

@Configuration
public class WebConfig {

    @Autowired
    private ApiKeyValidationFilter apiKeyValidationFilter;

    @Bean
    public FilterRegistrationBean<ApiKeyValidationFilter> loggingFilter() {
        FilterRegistrationBean<ApiKeyValidationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(apiKeyValidationFilter);
        registrationBean.addUrlPatterns("/soccer/*");
        return registrationBean;
    }
}
