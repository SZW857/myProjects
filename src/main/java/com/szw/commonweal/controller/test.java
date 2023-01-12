package com.szw.commonweal.controller;

import com.szw.commonweal.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
public class test {
    @CrossOrigin
    @RequestMapping("/test")
    @ResponseBody
    public User hello(User user){

        System.out.println("这一次"+user.getUserName());

        return user;
    }



}
