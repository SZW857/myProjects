package com.szw.commonweal.configuration;


import com.szw.commonweal.controller.interceptor.CrosInterceptor;
import com.szw.commonweal.controller.interceptor.LoginInterceptor;
import com.szw.commonweal.controller.interceptor.LoggerInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
private static final List<String> EXCLUDE_PATH= Arrays.asList(
        "/","/favicon.ico","/error","/*.html","/css/**","/js/**","/picture/**","/file/**");

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private CrosInterceptor crosInterceptor;

    @Autowired
    private LoggerInterceptor loggerInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //添加映射路径
        registry.addMapping("/**")
                //是否发送Cookie
                .allowCredentials(true)
                //设置放行哪些原始域   SpringBoot2.4.4下低版本使用.allowedOrigins("*")
                .allowedOriginPatterns("*")
                .allowedMethods("GET","POST")
                .maxAge(3600)
                //放行哪些请求方式
                .allowedMethods("*") //或者放行全部
                //放行哪些原始请求头部信息
                .allowedHeaders("*")
                //暴露哪些原始请求头部信息
                .exposedHeaders("*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(crosInterceptor);
        registry.addInterceptor(loggerInterceptor);
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns(EXCLUDE_PATH);
    }
}
