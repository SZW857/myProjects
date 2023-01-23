package com.szw.commonweal.service;

import com.szw.commonweal.entity.News;
import com.szw.commonweal.entity.ResultInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface NewsInfoService {

    /**
     * 管理员发布社区新闻
     * */

    public ResultInfo<String> PublishNews(String title,String type,String content,String adminId);

    /**
     * 获取发布的社区新闻
     * */
    public List getNews();

}
