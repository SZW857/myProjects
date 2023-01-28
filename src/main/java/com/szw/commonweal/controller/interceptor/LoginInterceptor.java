package com.szw.commonweal.controller.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.szw.commonweal.utils.JwtUtils;
import com.szw.commonweal.utils.ResponseMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("路经"+request.getRequestURI());

        //放行
        if (Accessible(request)){
            return true;
        }else {
        //拦截
            String token=request.getParameter("token");
            String email=request.getParameter("email");
            System.out.println("史泽文token为"+token);
            System.out.println("史泽文的email为"+email);

            Map<String, String> map = JwtUtils.verify(token);
            String value = map.get("msg");
            if (value=="无效签名"){
                map.put("state","false");
                ResponseMessage.resMassage(response,map);
                return false;
            }else if (value=="TOKEN过期"){
                map.put("state","false");
                ResponseMessage.resMassage(response,map);
                return false;
            }else if (value=="算法异常"){
                map.put("state","false");
                ResponseMessage.resMassage(response,map);
                return false;
            }else if (value=="TOKEN异常"){
                map.put("state","false");
                ResponseMessage.resMassage(response,map);
                return false;
            }else {
                return true;
            }
        }
    }


    //放行的路径
    private boolean Accessible(HttpServletRequest request){
        return
//=======================================志愿者==========================================================================
                request.getRequestURI().contains("/user/login") ||                          //用户登录
                request.getRequestURI().equals("/user/register")||                          //用户注册
                request.getRequestURI().contains("/user/changPasswd_F")||                   //志愿者修改密码(忘记密码)
                request.getRequestURI().contains("/user/NameCheck")||                       //用户名检测
                request.getRequestURI().equals("/user/telephoneCheck")||                    //手机号检测
                request.getRequestURI().equals("/user/idCardCheck")||                       //身份证检测
                request.getRequestURI().contains("/user/emailCheck")||                      //QQ邮箱检测
//=======================================管理员==========================================================================
                request.getRequestURI().contains("/admin/login")||                          //管理员登录
                request.getRequestURI().contains("/admin/NameCheck")||                      //管理员用户名检测
                request.getRequestURI().equals("/admin/EmailCheck")||                       //管理员QQ邮箱检测
                request.getRequestURI().contains("/admin/loginEmail")||                     //管理员QQ邮箱登录
                request.getRequestURI().equals("/admin/idCardCheck")||                      //管理员身份证检测
                request.getRequestURI().equals("/admin/changPasswd_F")||                    //管理员修改密码(忘记密码)
//=======================================发送接口========================================================================
                request.getRequestURI().equals("/sendMessage")||                            //发送短信
                request.getRequestURI().equals("/sendEmail")||                              //发送邮件
//=======================================社区新闻========================================================================
                request.getRequestURI().equals("/admin/selectAdminInfo")||                  //获取管理员
                request.getRequestURI().equals("/admin/getNewsInfo")||                      //获取社区新闻
//=======================================荣誉排行========================================================================
                request.getRequestURI().contains("/rankVolunteers")||                       //前十好市民
//=======================================获取志愿活动信息==================================================================
                request.getRequestURI().contains("/project/getProjectInformation")||        //获取志愿活动信息
                request.getRequestURI().equals("/project/getDetailPage")||                  //加载取活动详情页
//=======================================测试接口========================================================================
                request.getRequestURI().contains("/measure");                          //测试接口
    }

    //拦截的路径
    private  boolean Inaccessible(HttpServletRequest request){
        return
//=======================================志愿者===============================================================
                request.getRequestURI().contains("/user/changPasswd_P")||   //修改密码
//=======================================管理员=============================================================
                request.getRequestURI().contains("/news/publishNews")||     //发布新闻
//=======================================发送接口=============================================================

//=======================================社区新闻===========================================================
                request.getRequestURI().contains("/share/hasFetchCode")||
                request.getRequestURI().equals("/user/changePasswd_P")||    //修改密码
                request.getRequestURI().contains("/share/checkFetchCode")||
                request.getRequestURI().contains("/share/downShareFile")||
                request.getRequestURI().equals("/download/getFile")||
                request.getRequestURI().equals("/test/test")||
                request.getRequestURI().equals("/download/preLoad");
    }

}
