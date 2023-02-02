package com.szw.commonweal.controller;


import com.szw.commonweal.entity.Project;
import com.szw.commonweal.entity.ResultInfo;

import com.szw.commonweal.service.ManagerService;
import com.szw.commonweal.service.NewsInfoService;

import com.szw.commonweal.utils.UploadUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import javax.websocket.server.PathParam;
import java.io.IOException;
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
     * 管理员修改邮箱(DONE)*
     * */
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/changEmail")
    public ResultInfo<String> changeEmail(@RequestParam("adminName")String adminName,@RequestParam("email")String email){
        return managerService.changEmail(adminName,email);
    }

    /**
     * 管理员修改手机号(DONE)*
     * */
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/changTelephoneNumber")
    public ResultInfo<String> changeTelephone(@RequestParam("adminName")String adminName, @RequestParam("telephone")String telephone){
        return managerService.changTelephone(adminName,telephone);
    }

    /**
     * 管理员审核注册(DONE)*
     * */
    @CrossOrigin
    @RequestMapping("/passVerify")
    public ResultInfo<String> passVerify(@RequestParam("userId")String userId){
        return managerService.verifyVolunteers(userId);
    }

    /**
     * 管理员个人页展示(DONE)
     * */
    @CrossOrigin
    @RequestMapping("/selectOneAdminInfo")
    public List getOneAdminInfo(@RequestParam("adminName")String adminName){
        return managerService.getOneManagerInfo(adminName);
    }

    /**
     * 管理员自动清除不合法的注册者(DONE)*
     * */
    @CrossOrigin
    @RequestMapping("/delVolunteer")
    public ResultInfo<String> cleanVolunteers(@RequestParam("userId")String userId){
        return managerService.cleanVolunteers(userId);
    }

    /**
     * 管理员上传志愿活动图片
     * */
     @SneakyThrows
     @CrossOrigin
     @RequestMapping("/upload")
     public ResultInfo<String> uploadActivePic(MultipartFile file) {
     return managerService.uploadActivePic(file);
     }

    /**
     * 管理员发布志愿活动
     * */
    @CrossOrigin
    @RequestMapping("/publishActive")
    public ResultInfo<String> publishActive(HttpServletRequest request){
        return managerService.publishProject(request);
    }

    /**
     * 上传志愿活动图片的接口 (DONE)
     * */
    @ResponseBody
    @RequestMapping("/uploadImage")
    public ResultInfo<String> measure(@RequestParam(value = "avatar",required = false) MultipartFile avatar) throws IOException{
        return UploadUtil.uploadImage(avatar);
    }

    /**
     *分页获取志愿者签到申请
     * */
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getSignInVolunteer/{currentPage}/{pageSize}")
    public ResultInfo<Object> getSignVolunteers(@PathVariable("currentPage")int currentPage,
                                                @PathVariable("pageSize")int pageSize){
        return managerService.getSignInItems(currentPage,pageSize);
    }

    /**
     * 用于删除志愿活动的接口 (DONE)
     * */
    @ResponseBody
    @RequestMapping("/deleteImage")
    public String measure(@RequestParam("path") String path) throws IOException{
        return UploadUtil.DeleteImage(path);
    }

    /**
     * 通过志愿者的签到审核
     * */
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/passVolunteersSignIn")
    public ResultInfo<String> passVolunteersSignIn(@RequestParam("userId")String userId,
                                                   @RequestParam("serialNum")Long serialNum){
        return managerService.passedVolunteerSignIn(userId,serialNum);
    }

    /**
     * 获取全部志愿者的求助信息
     * */
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getAllVolunteerHelpInfo")
    public ResultInfo<Object> getAllVolunteerHelpInfo(){
        return managerService.getVolunteerHelpInformation();
    }

    /**
     * 通过志愿者的求助审核并发布
     * */
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/passAllVolunteerHelpInfo")
    public ResultInfo<Object> passAllVolunteerHelpInfo(HttpServletRequest request){
        return managerService.passedVolunteerHelpInfo(request);
    }




}

