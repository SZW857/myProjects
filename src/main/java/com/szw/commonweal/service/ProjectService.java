package com.szw.commonweal.service;

import com.szw.commonweal.entity.Project;
import com.szw.commonweal.entity.ResultInfo;


public interface ProjectService {

    /**
     *获取志愿活动信息
     * */
    public ResultInfo<Object> getActiveItems(int currentPage,int pageSize);

    /**
     * 详情志愿页面
     * */
    public Project adminPublishActivity(String serialNum);


}
