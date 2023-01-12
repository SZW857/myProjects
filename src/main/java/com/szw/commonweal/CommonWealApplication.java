package com.szw.commonweal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@MapperScan("com.szw.commonweal.dao")
public class CommonWealApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonWealApplication.class, args);
    }

}
