package com.szw.commonweal.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ResponseMessage  {

    public static void resMassage(HttpServletResponse response, Map map) throws Exception{
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }
}
