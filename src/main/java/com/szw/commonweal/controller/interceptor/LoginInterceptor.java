package com.szw.commonweal.controller.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.szw.commonweal.utils.jwtutils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");
        Map<String, String> map = jwtutils.verify(token);
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
    private void resMassage(HttpServletResponse response,Map map) throws Exception{
            String json = new ObjectMapper().writeValueAsString(map);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(json);
    }
}
