package com.szw.commonweal.component;

import org.springframework.stereotype.Component;

/**
 * base64加密工具类
 * */
public class Base64 {

    public String Lock(String password){
        byte[] bytes=password.getBytes();
        return java.util.Base64.getEncoder().encodeToString(bytes);
    }

    public String unLock(String key) {
        byte[] bytes= java.util.Base64.getDecoder().decode(key);
        return new String(bytes);
    }

}
