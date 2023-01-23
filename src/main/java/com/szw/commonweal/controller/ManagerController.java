package com.szw.commonweal.controller;


import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.service.ManagerService;
import com.szw.commonweal.service.NewsInfoService;
import com.szw.commonweal.utils.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private NewsInfoService newsInfoService;

    /**
     * 提供管理员的名单(DONE)
     * */
    @CrossOrigin
    @RequestMapping("/selectAdminInfo")
    public List getAdminInfo(){
        return managerService.getManagerInfo();
    }

    /**
     * 获取发布的新闻(DONE)
     * */
    @CrossOrigin
    @RequestMapping("/getNewsInfo")
    public List getNews(){
        return newsInfoService.getNews();
    }

    /**
     * 获取注册的志愿者(DONE)
     * */
    @CrossOrigin
    @RequestMapping("/getVolunteersInfo")
    public List getVolunteers(){
        return managerService.getVolunteers();
    }

    /**
     * 管理员登录名称检测(DONE)
     * */
    @CrossOrigin
    @RequestMapping("/NameCheck")
    public ResultInfo<String> AdminNameCheck(@RequestParam("adminId") String adminId){
        return  managerService.userNameCheck(adminId);
    }

    /**
     * 管理员邮箱检测(DONE)
     * */
    @CrossOrigin
    @RequestMapping("/EmailCheck")
    public ResultInfo<String> EmailCheck(@RequestParam("email") String email){
        return  managerService.EmailCheck(email);
    }

    /**
     * 管理员账号登录(DONE)
     * */
    @CrossOrigin
    @RequestMapping("/login")
    public ResultInfo<String> Login(@RequestParam("adminId") String adminId, @RequestParam("passwd") String passwd){
        return  managerService.checkLogin(adminId,passwd);
    }

    /**
     * 管理员邮箱登录(DONE)
     * */
    @CrossOrigin
    @RequestMapping("/loginEmail")
    public ResultInfo<String> EmailCheck(@RequestParam("email") String email, @RequestParam("passwd") String passwd){
        return  managerService.checkEmailLogin(email,passwd);
    }

    /**
     * 管理员身份证号检测(DONE)
     * */
    @CrossOrigin()
    @RequestMapping("/idCardCheck")
    @ResponseBody
    public ResultInfo<String> idCheck(HttpServletRequest request){
        return managerService.idCheck(request.getParameter("idCard"));
    }

    /**
     * 管理员修改密码(忘记密码)(DONE)
     * */
    @CrossOrigin
    @RequestMapping("/changPasswd_F")
    public ResultInfo<String> changePasswd_F(@RequestParam("idCard")String idCard, @RequestParam("passwd")String passwd,
                                           @PathParam("falseCode")String falseCode){
            return managerService.changePasswd_F(idCard,passwd,falseCode);
    }

    /**
     * 管理员修改密码(DONE)*
     * */
    @CrossOrigin
    @RequestMapping("/changPasswd")
    public ResultInfo<String> changePasswd(@RequestParam("adminId")String adminId, @RequestParam("passwd")String passwd){
        return managerService.changePasswd(adminId,passwd);
    }

    /**
     * 管理员审核注册(DONE)*
     * */
    @CrossOrigin
    @RequestMapping("/passVerify")
    public ResultInfo<String> passVerify(@RequestParam("userId")String userId){
        return managerService.verifyVolunteers(userId);
    }

}

