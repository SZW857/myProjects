package com.szw.commonweal.controller;

import com.szw.commonweal.entity.Project;
import com.szw.commonweal.entity.ResultInfo;

import com.szw.commonweal.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
public class ProjectTransactions {

    @Autowired
    private ProjectService projectService;

    /**
     * 加载志愿活动项目
     * */
    @CrossOrigin
    @RequestMapping("/getProjectInformation/{currentPage}/{pageSize}")
    public ResultInfo<Object> getActiveItems(@PathVariable("currentPage")int currentPage,
                                             @PathVariable("pageSize")int pageSize){
        System.out.println("史泽文哈哈参数"+currentPage+"||"+pageSize);
        return projectService.getActiveItems(currentPage,pageSize);
    }

    /**
     *志愿活动详情页
     * */
    @CrossOrigin
    @RequestMapping("/getDetailPage")
    public Project getActiveDetail(@RequestParam("serialNum")String serialNum){
        return projectService.adminPublishActivity(serialNum);
    }


}
