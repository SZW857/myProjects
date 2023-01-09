package com.szw.commonweal.controller;

import com.szw.commonweal.utils.DeCode;
import com.szw.commonweal.utils.SendMessage;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SendCodeController {
    @CrossOrigin
    @RequestMapping("/sendMessage")
    public String phoneCode(HttpServletRequest request) throws Exception {
        String telephoneNum=request.getParameter("tele");
        String falseCode=request.getParameter("code");
        //解码前端传来的加密参数，并返回
        return  SendMessage.send(telephoneNum,DeCode.deCode(falseCode));
    }
}
