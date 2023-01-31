package com.szw.commonweal.service.Impl;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szw.commonweal.dao.AttendanceMapper;
import com.szw.commonweal.dao.ProjectMapper;

import com.szw.commonweal.entity.Project;
import com.szw.commonweal.entity.ResultInfo;


import com.szw.commonweal.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private AttendanceMapper attendanceMapper;

    /**
     *分页获取志愿活动
     * */
    @Override
    public ResultInfo<Object> getActiveItems(int currentPage,int pageSize) {
        ResultInfo<Object> res = new ResultInfo<>();
        Page<Project> page = new Page<>(currentPage,pageSize);
        Page<Project> mapsPage = projectMapper.selectPage(page, null);
        long total = mapsPage.getTotal();
        List<Project> list = mapsPage.getRecords();
        if (!list.isEmpty()){
            res.setData(list);
            res.setTotal(total);
            res.setStatus(ResultInfo.SUCCESS);
            res.setCode(200);
        }else {
            res.setData("获取志愿项目失败");
        }
        return res;
    }

    /**
     * 管理员发布志愿活动
     * */
    @Override
    public Project adminPublishActivity(String serialNum) {
        Project project = projectMapper.selectById(serialNum);
        return project;
    }


}
