package com.szw.commonweal.service;

import com.szw.commonweal.entity.views.GetVolunteers;

import java.util.List;

public interface IndexService {

    /**
     * 获取全部的用户数
     * */
    public List<GetVolunteers> getAllVolunteers();
}
