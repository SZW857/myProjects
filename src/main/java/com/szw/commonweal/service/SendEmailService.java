package com.szw.commonweal.service;


import com.szw.commonweal.entity.ResultInfo;

import javax.servlet.http.HttpServletRequest;

public interface SendEmailService {
    /**
     * 用户注册--获取邮箱验证码
     * */
    public ResultInfo<String> createRegisterEmailCode(String falseCode, String email, HttpServletRequest request);
}
