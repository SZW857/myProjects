package com.szw.commonweal.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szw.commonweal.dao.GetVolunteerMapper;
import com.szw.commonweal.dao.NewsMapper;
import com.szw.commonweal.entity.News;
import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.entity.Volunteer;
import com.szw.commonweal.service.NewsInfoService;
import com.szw.commonweal.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsInfoServiceImpl implements NewsInfoService {
    @Autowired
    private NewsMapper newsMapper;

    /**
     *管理员发布社区新闻
     * */
    @Override
    public ResultInfo<String> PublishNews(String title,String type,String content,String adminId) {
        ResultInfo<String> res = new ResultInfo<>();
        News news = new News();
        news.setTitle(title);
        news.setType(type);
        news.setAdminId(adminId);
        news.setContent(content);
        int i = newsMapper.insert(news);
        if (i!=1){
            res.setStatus(ResultInfo.FAIL);
            res.setData("发布失败");
        }else {
            res.setStatus(ResultInfo.SUCCESS);
            res.setData("发布成功");
        }
        return res;
    }

    /**
     * 获取发布的新闻(DONE)
     * */
    @Override
    public List getNews() {
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        wrapper.select("news_id","title","content","type","create_time");
        List<News> list = newsMapper.selectList(wrapper);
        return list;
    }

}

