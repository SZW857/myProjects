package com.szw.commonweal.configuration;


import com.szw.commonweal.controller.interceptor.CrosInterceptor;
import com.szw.commonweal.controller.interceptor.LoginInterceptor;
import com.szw.commonweal.controller.interceptor.LoggerInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private CrosInterceptor crosInterceptor;

    @Autowired
    private LoggerInterceptor loggerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(crosInterceptor);
        registry.addInterceptor(loggerInterceptor);
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/measure");
    }
}
