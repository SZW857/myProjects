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
     * 用户名检测重复或是否存在
     * */
    public ResultInfo<String> userNameCheck(String userName);

    /**
     * 用户登录检测
     * */
    public ResultInfo<String> checkLogin(String userId,String passwd);

    /**
     * 用户信息返回
     * */
    public ResultInfo<String> backUserInfo();

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
    public ResultInfo<String> changePasswd(String passwd,String idCard);

    /**
     * 用户修改密码
     * */
    public ResultInfo<String> changPasswd_P(String userId,String passwd);

    /**
     * 用户注册
     * */
    public ResultInfo<String> userRegister(Volunteer volunteer);

    /**
     * 用户注册
     * */
    public List PersonInfo(String userId);




}




