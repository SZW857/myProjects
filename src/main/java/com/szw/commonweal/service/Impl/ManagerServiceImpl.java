package com.szw.commonweal.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szw.commonweal.dao.MangerMapper;
import com.szw.commonweal.entity.Manager;
import com.szw.commonweal.entity.ResultInfo;


import com.szw.commonweal.entity.Volunteer;
import com.szw.commonweal.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private MangerMapper mangerMapper;
    ResultInfo<String> res = new ResultInfo<>();
    @Override
    public List<Manager> getManager() {
        List<Manager> list = mangerMapper.selectList(null);
        System.out.println(list);
        if (!list.isEmpty()){
            res.setStatus(ResultInfo.SUCCESS);
            res.setData("已返回管理员全部的数据");
        }else {
            res.setStatus(ResultInfo.FAIL);
            res.setData("管理员数据加载失败");
        }
        return  list;
    }




}


