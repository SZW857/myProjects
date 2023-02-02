package com.szw.commonweal.controller;

import com.szw.commonweal.entity.Help;
import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.entity.Volunteer;
import com.szw.commonweal.entity.views.EnrollResult;
import com.szw.commonweal.service.HelpService;
import com.szw.commonweal.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;



@Controller
@RequestMapping("/user")
public class VolunteerController {
    @Autowired
    private VolunteerService volunteerService;
    @Autowired
    private HelpService helpService;

    /**
     * 用户身份证号检测 (DONE)
     * */
    @CrossOrigin()
    @RequestMapping("/idCardCheck")
    @ResponseBody
    public ResultInfo<String> idCheck(HttpServletRequest request){
        return volunteerService.idCheck(request.getParameter("idCard"));
    }

    /**
     * 手机号检测 (DONE)
     * */
    @CrossOrigin()
    @RequestMapping("/telephoneCheck")
    @ResponseBody
    public ResultInfo<String> telephoneCheck(HttpServletRequest request){
        return volunteerService.telephoneCheck(request.getParameter("telephone"));
    }

    /**
     * 用户名检测 (DONE)
     * */
    @CrossOrigin
    @RequestMapping("/NameCheck")
    @ResponseBody
    public ResultInfo<String> userId(HttpServletRequest request){
        return volunteerService.userNameCheck(request.getParameter("userId"));
    }

    /**
     * QQ邮箱检测 (DONE)
     * */
    @CrossOrigin
    @RequestMapping("/emailCheck")
    @ResponseBody
    public ResultInfo<String> emailCheck(HttpServletRequest request){
        return volunteerService.emailCheck(request.getParameter("email"));
    }

    /**
     * 用户注册(DONE)
     * */
    @CrossOrigin
    @RequestMapping("/register")
    @ResponseBody
    public ResultInfo<String> Register(Volunteer volunteer){
        return volunteerService.userRegister(volunteer);
    }

    /**
     * 个人页面展示(DONE)
     * */
    @CrossOrigin
    @RequestMapping("/selectPersonInfo")
    @ResponseBody
    public List personalPage(@RequestParam("userId")String userId){
        return volunteerService.PersonInfo(userId);
    }

    /**
     * 用户登录(DONE)
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
        return volunteerService.changPasswd_P(userId,passwd);
    }

    /**
     * 用户修改密码(忘记密码)
     * */
    @CrossOrigin
    @RequestMapping("/changPasswd_F")
    public ResultInfo<String> changePasswd(@RequestParam("idCard")String idCard,
                                           @RequestParam("passwd")String passwd,
                                           @PathParam("falseCode")String falseCode) {
        return volunteerService.changePasswd(idCard,passwd,falseCode);
    }

    /**
     * 用户修改QQ邮箱(DONE)
     * */
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/changEmail")
    public ResultInfo<String> changeEmail(@RequestParam("userId")String userId,@RequestParam("email")String email){
        return volunteerService.changEmail(userId,email);
    }

    /**
     * 用户修改手机号 **(DONE)
     * */
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/changTelephoneNumber")
    public ResultInfo<String> changeTelephone(@RequestParam("userId")String userId, @RequestParam("telephone")String telephone){
        return volunteerService.changTelephone(userId,telephone);
    }

    /**
     * 用户保存个人信息*(DONE)
     * */
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/saveInformation")
    public ResultInfo<String> saveInformation(@RequestParam("userId")String userId,
                                              @RequestParam("address")String address,
                                              @RequestParam("sex")String sex,
                                              @RequestParam("age") int age){
        return volunteerService.saveInformation(userId,address,sex,age);
    }

    /**
     * 获取报名结果集
     * */
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getEnrollResult")
    public List<EnrollResult> backResult(@RequestParam("userId")String userId){
        return volunteerService.enrollResult(userId);
    }

    /**
     *志愿者退选活动
     * */
    @CrossOrigin
    @RequestMapping("/deleteOpt")
    @ResponseBody
    public ResultInfo<String> optOut(@RequestParam("userId") String userId,@RequestParam("serialNum") String serialNum){
       return volunteerService.optOut(userId,serialNum);
    }

    /**
     * 志愿者提交求助信息
     * */
    @CrossOrigin
    @RequestMapping("/postHelpInformation")
    @ResponseBody
    public ResultInfo<String> postHelpActivity(HttpServletRequest request){
       return helpService.postHelpInfo(request);
    }
}


