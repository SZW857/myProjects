package com.szw.commonweal.controller;
import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.entity.User;
import com.szw.commonweal.utils.TokenDecrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@Controller
public class test {
    @CrossOrigin
    @RequestMapping("/hello")
    @ResponseBody
    public User hello(User user){

        System.out.println("这一次"+user.getUserName());

        return user;
    }



}
