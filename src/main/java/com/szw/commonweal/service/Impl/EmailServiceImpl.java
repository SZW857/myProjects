package com.szw.commonweal.service.Impl;


import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.service.SendEmailService;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;



@Service
public class EmailServiceImpl implements SendEmailService {
    @Resource
    private  JavaMailSenderImpl javaMailSender;

    /**
     * 获取邮箱验证码
     * */
    @Override
    public ResultInfo<String> createRegisterEmailCode(String realCode, String email, HttpServletRequest request) {
        ResultInfo<String> res = new ResultInfo<>();
        SimpleMailMessage mail = new SimpleMailMessage();
        /*
         * 传递邮箱验证码
         * */
        String content = request.getParameter("content");
        System.out.println("传过来的"+content);
        String code=realCode;
        if(isNumeric(code)){
        /*
         * 发送邮件
         * */
        mail.setFrom("1448602918@qq.com");
        mail.setTo(email);
        mail.setSubject("社区公益服务注册验证码");
        mail.setText("社区公益服务系统提醒您的注册验证码如下：" + code + "，五分钟内有效！");
        mail.setSentDate(new Date());
        try {
            javaMailSender.send(mail);
            res.setData("已发送至QQ邮箱");
            res.setStatus(ResultInfo.SUCCESS);
        } catch (MailException e) {
            res.setData("发送至QQ邮箱失败");
            res.setStatus(ResultInfo.FAIL);
            e.printStackTrace();
        }
        /*
         * 返回结果
         * */

    }else {
            mail.setFrom("1448602918@qq.com");
            mail.setTo(email);
            mail.setSubject("社区公益服务审核问题告知");
            mail.setText("社区公益服务系统提醒您，你的注册存在的问题如下：" + content + "，请认真核查后再次提交，感谢配合");
            mail.setSentDate(new Date());
            try {
                javaMailSender.send(mail);
                res.setData("已发送至该用户的QQ邮箱");
                res.setStatus(ResultInfo.SUCCESS);
            } catch (MailException e) {
                res.setData("发送至QQ邮箱失败");
                res.setStatus(ResultInfo.FAIL);
                e.printStackTrace();
            }
        }
            return res;
    }

    public static boolean isNumeric(String str){
        for(int i=str.length();--i>=0;){
            int chr=str.charAt(i);
            if(chr<48 || chr>57)
                return false;
        }
        return true;
    }
}
