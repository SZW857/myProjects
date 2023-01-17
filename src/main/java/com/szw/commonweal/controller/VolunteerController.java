package com.szw.commonweal.controller;

import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.entity.Volunteer;
import com.szw.commonweal.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/user")
public class VolunteerController {
    @Autowired
    private VolunteerService volunteerService;

    /**
     * 用户身份证号检测 (DONE)
     * */
    @CrossOrigin()
    @RequestMapping("/idCardCheck")
    @ResponseBody
    public ResultInfo<String> idCheck(HttpServletRequest request){
        String idCard=request.getParameter("idCard");
        return volunteerService.idCheck(idCard);
    }

    /**
     * 手机号检测 (DONE)
     * */
    @CrossOrigin()
    @RequestMapping("/telephoneCheck")
    @ResponseBody
    public ResultInfo<String> telephoneCheck(HttpServletRequest request){
        String telephoneNum=request.getParameter("telephoneNum");
        return volunteerService.telephoneCheck(telephoneNum);
    }

    /**
     * 用户名检测 (DONE)
     * */
    @CrossOrigin
    @RequestMapping("/NameCheck")
    @ResponseBody
    public ResultInfo<String> userId(HttpServletRequest request){
        String userId=request.getParameter("userId");
        System.out.println("史泽文"+userId);
        return volunteerService.userNameCheck(userId);

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

    /**
     * 个人页面展示
     * */
    @CrossOrigin
    @RequestMapping("/selectPersonInfo")
    @ResponseBody
    public List personalPage(@RequestParam("userId")String userId){
        return volunteerService.PersonInfo(userId);
    }

    /**
     * 用户登录页(DONE)
     * */
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/login")
    public ResultInfo<String> Login(@RequestParam("userId") String userId, @RequestParam("passwd") String passwd){
        return  volunteerService.checkLogin(userId,passwd);
    }

    /**
     * 用户修改密码 (DONE)
     * */
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/changePasswd_P")
    public ResultInfo<String> changePasswd_P(@RequestParam("userId")String userId,@RequestParam("passwd")String passwd){
        System.out.println("前端穿的密码"+passwd);
        return volunteerService.changPasswd_P(userId,passwd);
    }

    /**
     * 用于测试的接口 (DONE)
     * */
    @ResponseBody
    @RequestMapping("/measure")
        public String measure(){
            return "ok";
        }
}


