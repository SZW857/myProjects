package com.szw.commonweal.controller;
import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.entity.Volunteer;
import com.szw.commonweal.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class VolunteerController {
    @Autowired
    private VolunteerService volunteerService;

    /**
     * 用户名检测
     * */
    @RequestMapping("/usernamecheck/{userId}")
    @ResponseBody
    public ResultInfo<String> userId(@PathVariable("userId")String userId){
        System.out.println(userId);
        return volunteerService.userNameCheck(userId);
    }

    /**
     * 用户登录检测
     * */
    @ResponseBody
    @RequestMapping("/login")
    public ResultInfo<String> logIn(Volunteer volunteer){
        System.out.println("controller层密码"+volunteer.getPasswd());
        System.out.println("controller层用户名"+volunteer.getUserId());
        return volunteerService.checkLogin(volunteer.getUserId(),volunteer.getPasswd());
    }

    /**
     * 用户身份证号检测
     * */
    @ResponseBody
    @RequestMapping("/idcardcheck/{idcard}")
    public ResultInfo<String> idCheck(@PathVariable("idcard")String idCard){
        System.out.println(idCard);
        return volunteerService.idCheck(idCard);
    }

    /**
     * 用户修改密码
     * */
    @ResponseBody
    @RequestMapping("/changpasswd")
    public ResultInfo<String> changePasswd(Volunteer volunteer){
        System.out.println("前端穿的密码"+volunteer.getPasswd());
        return volunteerService.changePasswd(volunteer.getPasswd(),volunteer.getIdCard());
    }

    /**
     * 用户注册
     * */
    @ResponseBody
    @RequestMapping("/register")
    public ResultInfo<String> Register(Volunteer volunteer){
        System.out.println("前端穿的密码"+volunteer.getPasswd());
        return volunteerService.userRegister(volunteer);
    }


}


