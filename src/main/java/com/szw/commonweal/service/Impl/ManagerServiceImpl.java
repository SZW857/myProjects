package com.szw.commonweal.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szw.commonweal.dao.MangerMapper;
import com.szw.commonweal.entity.Manager;


import com.szw.commonweal.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private MangerMapper mangerMapper;
    @Override
    public List getManagerInfo() {
        QueryWrapper<Manager> wrapper = new QueryWrapper<>();
        wrapper.select("admin_name","admin_id");
        List<Map<String, Object>> mapList = mangerMapper.selectMaps(wrapper);
        System.out.println(mapList);
        return mapList;
    }
}


