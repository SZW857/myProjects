package com.szw.commonweal.controller;

import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.service.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/help")
public class HelpInformationController {

    @Autowired
    private HelpService helpService;

    /**
     * 获取志愿者发布的求助信息详情页
     * */
    @CrossOrigin
    @RequestMapping("/getVolunteersHelpInformation")
    public ResultInfo<Object> getVolunteersHelpInformation(@RequestParam("helpNum")String helpNum){
        return helpService.getDetailPage(helpNum);
    }

    /**
     * 获取求助者的信息详情页
     * */
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getHelpInfoDetail")
    public ResultInfo<Object> getHelpInfoDetail(@RequestParam("helpNum") String helpNum){
         return helpService.getHelpInfoDetail(helpNum);
    }

    /**
     * 清除不合理的志愿者申请求助信息
     * */
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/delHelpInfoDetail")
    public ResultInfo<String> delHelpInfoDetail(@RequestParam("helpNum") String helpNum){
        return helpService.delVolunteerHelpInfo(helpNum);
    }


}
