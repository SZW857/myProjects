package com.szw.commonweal.controller.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.szw.commonweal.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //放行
        if (Accessible(request)){
            return true;
        }else {
        //拦截
            String token=request.getParameter("token");
            System.out.println("史泽文token为"+token);
            Map<String, String> map = JwtUtils.verify(token);
            String value = map.get("msg");
            if (value=="无效签名"){
                map.put("state","false");
                resMassage(response,map);
                return false;
            }else if (value=="TOKEN过期"){
                map.put("state","false");
                resMassage(response,map);
                return false;
            }else if (value=="算法异常"){
                map.put("state","false");
                resMassage(response,map);
                return false;
            }else if (value=="token异常"){
                map.put("state","false");
                resMassage(response,map);
                return false;
            }else {
                return true;
            }
        }
    }

    private void resMassage(HttpServletResponse response,Map map) throws Exception{
            String json = new ObjectMapper().writeValueAsString(map);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(json);
    }
    //放行的路径
    private boolean Accessible(HttpServletRequest request){
        return request.getRequestURI().equals("/user/login") ||             //登录
                request.getRequestURI().equals("/user/register")||          //注册 telephoneCheck
                request.getRequestURI().equals("/user/sendMessage")||       //发送短信
                request.getRequestURI().contains("/user/changPasswd_F")||   //修改密码(忘记密码)
                request.getRequestURI().contains("/user/NameCheck")||       //用户名检测
                request.getRequestURI().contains("/user/telephoneCheck")||  //手机号检测
                request.getRequestURI().equals("/user/idCardCheck")||       //身份证检测
                request.getRequestURI().equals("/admin/selectAdminInfo")||   //获取管理员
                request.getRequestURI().equals("/user/XXX")||
                request.getRequestURI().contains("/user/measure");          //测试接口

    }
    //拦截的路径
    private  boolean Inaccessible(HttpServletRequest request){
        return  request.getRequestURI().contains("/share/hasFetchCode")||
                request.getRequestURI().equals("/user/changePasswd_P")||    //修改密码
                request.getRequestURI().contains("/share/checkFetchCode")||
                request.getRequestURI().contains("/share/downShareFile")||
                request.getRequestURI().equals("/download/getFile")||
                request.getRequestURI().equals("/test/test")||
                request.getRequestURI().equals("/download/preLoad");
    }

}
