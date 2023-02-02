package com.szw.commonweal.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.szw.commonweal.entity.Help;
import com.szw.commonweal.entity.ResultInfo;

import javax.servlet.http.HttpServletRequest;

public interface HelpService {

    /**
     * 志愿者提交求助申请
     * */
    public ResultInfo<String> postHelpInfo(HttpServletRequest request);


    /**
     * 获取志愿者发布的求助申请
     * */
    public ResultInfo<Object> getDetailPage(String helpNum);

    /**
     * 志愿者求助页详情
     * */
    public ResultInfo<Object> getHelpInfoDetail(String helpNum);

    /**
     * 清除不合理的志愿者申请求助信息
     * */
    public ResultInfo<String> delVolunteerHelpInfo(String helpNum);

}
