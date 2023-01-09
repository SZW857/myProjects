package com.szw.commonweal.configuration;

import com.szw.commonweal.component.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Base64Configuration {
//this is a bea
    @Bean

    public Base64 base64(){
        return new Base64();
    }

}
