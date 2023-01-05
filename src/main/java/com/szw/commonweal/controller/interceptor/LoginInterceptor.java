package com.szw.commonweal.controller.interceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * */
@Component
public class LoginInterceptor implements HandlerInterceptor {
;

    @CrossOrigin
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*
        * 获取token
        * */
        String token = request.getHeader("token");

        /*
         * 检验携带token
         * */
        if(token == null){
            return urlAccessable(request);
        }else{
            String userId = (token);
            if(userId == null){
                return urlAccessable(request);
            }else{
                request.setAttribute("userId",userId);
                return true;
            }
        }
    }

    private boolean urlAccessable(HttpServletRequest request){
        return request.getRequestURI().equals("/user/login") ||
                request.getRequestURI().equals("/user/register")||
                request.getRequestURI().equals("/user/getEmailCode")||
                request.getRequestURI().equals("/user/permitUser")||
                request.getRequestURI().contains("/user/getUserPic")||
                request.getRequestURI().contains("/share/hasFetchCode")||
                request.getRequestURI().contains("/share/checkFetchCode")||
                request.getRequestURI().contains("/share/downShareFile")||
                request.getRequestURI().equals("/download/getFile")||
                request.getRequestURI().equals("/test/test")||
                request.getRequestURI().equals("/download/preLoad");
    }
}
