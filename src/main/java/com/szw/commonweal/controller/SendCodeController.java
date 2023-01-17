package com.szw.commonweal.controller;

import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.service.VolunteerService;
import com.szw.commonweal.utils.DeCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class SendCodeController {
    public static int VERIFY_CODE;
    @Autowired
    private VolunteerService volunteerService;
    /**
     * 发送短信接口
     * */
    @CrossOrigin
    @RequestMapping("/user/sendMessage")
    public String phoneCode(@RequestParam("falseCode")String falseCode,@RequestParam("telephoneNum")String telephoneNum) throws Exception {
        VERIFY_CODE=DeCode.deCode(falseCode);
        System.out.println("手机号"+telephoneNum);
        System.out.println("验证码"+falseCode);
        System.out.println("解密后："+VERIFY_CODE);
        //解码前端传来的加密参数，并返回
//        return  SendMessage.send(telephoneNum,VERIFY_CODE);
        return "";
    }

    /**
     * 用户修改密码(忘记密码)
     * */
    @CrossOrigin
    @RequestMapping("/user/changPasswd_F")
    public ResultInfo<String> changePasswd(@RequestParam("idCard")String idCard, @RequestParam("passwd")String passwd, @PathParam("valueCode")String valueCode){
        ResultInfo<String> resultInfo = new ResultInfo<>();
        System.out.println("XIU手机号"+idCard);
        System.out.println("XIU前端穿的密码"+passwd);
        System.out.println("XIU："+VERIFY_CODE);
        int i= Integer.parseInt(valueCode);
        if (i==VERIFY_CODE){
            return volunteerService.changePasswd(passwd,idCard);
        }else {
            resultInfo.setData("修改密码失败");
            resultInfo.setStatus(ResultInfo.FAIL);
            return resultInfo;
        }
    }
}
