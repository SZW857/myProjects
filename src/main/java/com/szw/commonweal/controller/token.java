package com.szw.commonweal.controller;
import com.szw.commonweal.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class token {
    private final String username = "admin";
    private final String passwd = "123456";
    @CrossOrigin
    @RequestMapping("/token")
    @ResponseBody
    public User hello(User user){
        if (username.equals(user.getUserName())&&passwd.equals(user.getPassWord())){
            //添加token
            System.out.println("这1回："+user.getUserName());
            return user;
        }
        System.out.println("这2回："+user.getUserName());
        return null;
    }

}
