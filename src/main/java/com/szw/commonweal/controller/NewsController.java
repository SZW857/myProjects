package com.szw.commonweal.controller;


import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.service.NewsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsInfoService newsInfoService;

    /**
     * 管理员发布社区新闻
     * */
    @CrossOrigin
    @RequestMapping("/publishNews")
    public ResultInfo<String> PublishNews(@RequestParam("title")String title,
                                          @RequestParam("type")String type,
                                          @RequestParam("content")String content,
                                          @RequestParam("adminId")String adminId){
        return newsInfoService.PublishNews(title,type,content,adminId);
    }
}
