package com.szw.commonweal.controller;

import com.szw.commonweal.entity.User;
import com.szw.commonweal.utils.TokenDecrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class hello {
    private final String username = "admin";
    private final String passwd = "123456";
    @CrossOrigin
    @RequestMapping("/hello")
    @ResponseBody
    public User hello(User user){
        if (username.equals(user.getUserName())&&passwd.equals(user.getPassWord())){
            //添加token
            user.setToken(TokenDecrypt.createToken());
            return user;
        }
        return null;
    }
    @GetMapping("/checkToken")
    public Boolean checkToken(HttpServletRequest request){
        String token = request.getHeader("token");
        return TokenDecrypt.checkToken(token);
    }
}
