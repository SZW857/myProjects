package com.szw.commonweal.service;
import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.entity.Volunteer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 用户名检测业务接口
 * @author szw
 * */
public interface VolunteerService {
    /**
     * 用户名检测是否存在
     * */
    public ResultInfo<String> userNameCheck(String userName);

    /**
     * Email检测是否存在
     * */
    public ResultInfo<String> emailCheck(String email);

    /**
     * 用户登录检测
     * */
    public ResultInfo<String> checkLogin(String userId,String passwd);

    /**
     * 用户身份证检测重复或是否存在
     * */
    public ResultInfo<String> idCheck(String idCard);

    /**
     * 用户身份证检测重复或是否存在
     * */
    public ResultInfo<String> telephoneCheck(String telephoneNum);

    /**
     * 用户修改密码(忘记密码)
     * */
    public ResultInfo<String> changePasswd(String idCard,String passwd,String falseCode);

    /**
     * 用户修改密码*
     * */
    public ResultInfo<String> changPasswd_P(String userId,String passwd);

    /**
     * 用户修改QQ邮箱*
     * */
    public ResultInfo<String> changEmail(String userId,String email);

    /**
     * 用户修改手机号*
     * */
    public ResultInfo<String> changTelephone(String userId,String telephone);

    /**
     * 用户保存个人信息*
     * */
    public ResultInfo<String> saveInformation(String userId,String address,String sex,int age);

    /**
     * 用户注册
     * */
    public ResultInfo<String> userRegister(Volunteer volunteer);

    /**
     * 个人页面展示*
     * */
    public List PersonInfo(String userId);




}




