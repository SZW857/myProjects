package com.szw.commonweal.controller;

import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.service.SendEmailService;
import com.szw.commonweal.service.VolunteerService;
import com.szw.commonweal.utils.Base64;
import com.szw.commonweal.utils.EphemeralCode;
import com.szw.commonweal.utils.SendTelephoneMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SendCodeController {

    @Autowired
    private SendEmailService emailService;
    /**
     * 发送短信接口(DONE)
     * */
    @CrossOrigin
    @RequestMapping("/sendMessage")
    public String phoneCode(@RequestParam("falseCode")String falseCode,
                            @RequestParam("telephone")String telephoneNum)throws Exception{
        EphemeralCode.TELEPHONEREALCODE=Base64.unLock(falseCode);
        System.out.println("手机号"+telephoneNum);
        System.out.println("验证码"+falseCode);
        System.out.println("解密后："+EphemeralCode.TELEPHONEREALCODE);
        //解码前端传来的加密参数，并返回
//        return SendTelephoneMessage.send(telephoneNum,EphemeralCode.TELEPHONEREALCODE);
        return null;
    }

    /**
     * 发送QQ邮箱接口(DONE)
     * */
    @CrossOrigin
    @RequestMapping("/sendEmail")
    public ResultInfo<String>AAA(
            @RequestParam("falseCode")String falseCode, @RequestParam("email") String email,HttpServletRequest request){
        EphemeralCode.EMAILREALCODE=Base64.unLock(falseCode);
        return emailService.createRegisterEmailCode(EphemeralCode.EMAILREALCODE,email,request);
    }


}
