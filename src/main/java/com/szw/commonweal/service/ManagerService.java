package com.szw.commonweal.service;

import com.szw.commonweal.entity.Manager;
import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.entity.Volunteer;

import java.util.List;

/**
 * 用户身份证检测业务接口
 * @author  史泽文
 * */
public interface ManagerService {
    /**
     * 提供管理员名单(用于注册)
     * */
    public List<Object> getManagerInfo();

    /**
     * 管理员名称检测是否存在
     * */
    public ResultInfo<String> userNameCheck(String userName);

    /**
     * 管理员邮箱检测是否存在
     * */
    public ResultInfo<String> EmailCheck(String email);

    /**
     * 管理员账号登录
     * */
    public ResultInfo<String> checkLogin(String adminId, String passwd);

    /**
     * 管理员邮箱登录
     * */
    public ResultInfo<String> checkEmailLogin(String adminId, String passwd);

    /**
     * 管理员身份证检测重复或是否存在
     * */
    public ResultInfo<String> idCheck(String idCard);

    /**
     * 管理员修改密码(忘记密码)
     * */
    public ResultInfo<String> changePasswd(String idCard,String passwd,String fLaseCode);




}
