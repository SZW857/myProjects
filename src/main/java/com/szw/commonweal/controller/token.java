package com.szw.commonweal.controller;
import com.szw.commonweal.entity.User;
import com.szw.commonweal.utils.TokenDecrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class token {
    private final String username = "admin";
    private final String passwd = "123456";
    @CrossOrigin
    @RequestMapping("/login545454")
    @ResponseBody
    public User hello(User user){
        if (username.equals(user.getUserName())&&passwd.equals(user.getPassWord())){
            //添加token
            user.setToken(TokenDecrypt.createToken());
            System.out.println("这8回："+user.getUserName());
            return user;
        }
        System.out.println("这2回："+user.getUserName());
        return null;
    }

    @GetMapping("/checkToken")
    public Boolean checkToken(HttpServletRequest request){
        String token = request.getHeader("token");
        return TokenDecrypt.checkToken(token);
    }


}
