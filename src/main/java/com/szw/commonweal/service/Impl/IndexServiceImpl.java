package com.szw.commonweal.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szw.commonweal.dao.GetVolunteerMapper;
import com.szw.commonweal.entity.views.GetVolunteers;
import com.szw.commonweal.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private GetVolunteerMapper getVolunteerMapper;

    /**
     * 获取全部的用户数
     * */
    @Override
    public List<GetVolunteers> getAllVolunteers() {
        QueryWrapper<GetVolunteers> wrapper = new QueryWrapper<>();
        wrapper.select("*");
        List<GetVolunteers> list = getVolunteerMapper.selectList(wrapper);
        return list;
    }
}
