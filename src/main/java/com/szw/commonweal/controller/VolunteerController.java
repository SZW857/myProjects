package com.szw.commonweal.controller;

import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.entity.Volunteer;
import com.szw.commonweal.service.VolunteerService;
import com.szw.commonweal.utils.jwtutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
public class VolunteerController {
    @Autowired
    private VolunteerService volunteerService;

    /**
     * 用户名检测
     * */
    @RequestMapping("/usernamecheck")
    @ResponseBody
    public String userId(@RequestParam("userId")String userId){
        System.out.println(userId);
        return "volunteerService.userNameCheck(userId)";
    }

    /**
     * 用户登录检测
     * */
    @ResponseBody
    @RequestMapping("/login")
    public ResultInfo<String> logIn(){
        return volunteerService.backUserInfo();
    }


    /**
     * 用户身份证号检测
     * */
    @ResponseBody
    @RequestMapping("/idcardcheck/{idcard}")
    public ResultInfo<String> idCheck(@PathVariable("idcard")String idCard){
        System.out.println(idCard);
        return volunteerService.idCheck(idCard);
    }

    /**
     * 用户修改密码
     * */
    @ResponseBody
    @RequestMapping("/changpasswd")
    public ResultInfo<String> changePasswd(Volunteer volunteer){
        System.out.println("前端穿的密码"+volunteer.getPasswd());
        return volunteerService.changePasswd(volunteer.getPasswd(),volunteer.getIdCard());
    }

    /**
     * 用户注册
     * */
    @ResponseBody
    @RequestMapping("/register")
    public ResultInfo<String> Register(Volunteer volunteer){
        System.out.println("前端穿的密码"+volunteer.getPasswd());
        return volunteerService.userRegister(volunteer);
    }

    /**
     * 用户登录页外检测
     * */
    @PostMapping("/user/test")
    @ResponseBody
    public Map<String, Object> TEST(HttpServletRequest request){
        String token = request.getHeader("token");
        Map<String, Object> map = new HashMap<>();
        //处理逻辑
        System.out.println("用户名"+ jwtutils.getVerify(token).getClaim("userId").asString());
        System.out.println("密码"+jwtutils.getVerify(token).getClaim("passwd").asString());
        map.put("state",true);
        map.put("msg","testController:请求成功");
        return map;
    }

    /**
     * 用户登录页
     * */
    @ResponseBody
    @RequestMapping("/user/login")
    public HashMap<String, String> AA(@RequestParam("NAME") String NAME, @RequestParam("PASSWD") String PASSWD){
        HashMap<String, String> map = new HashMap<>();
        if (NAME.equals("szw")&&PASSWD.equals("S751225241")){
            System.out.println("登陆成功"+NAME);
            map.put("id",NAME);
            map.put("pwd",PASSWD);

            String token = jwtutils.getToken(map);

            map.put("msg","登录成功");
            map.put("state","ok");
            map.put("token",token);
        }else {
            System.out.println("登陆失败");
            map.put("msg","登录失败");
            map.put("state","false");
        }
        return map;
    }


}


