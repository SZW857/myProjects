package com.szw.commonweal.controller;


import com.szw.commonweal.dao.NewsMapper;
import com.szw.commonweal.service.ManagerService;
import com.szw.commonweal.service.NewsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
@RestController
@RequestMapping("/admin")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private NewsInfoService newsInfoService;

    @CrossOrigin
    @RequestMapping("/selectAdminInfo")
    public List getAdminInfo(){
        return managerService.getManagerInfo();
    }


    @CrossOrigin
    @RequestMapping("/getNewsInfo")
    public List aaa(){

        return newsInfoService.getNews();
    }




}

