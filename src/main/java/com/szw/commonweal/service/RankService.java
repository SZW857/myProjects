package com.szw.commonweal.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szw.commonweal.entity.views.RankVolunteers;

import java.util.List;

public interface RankService {
    /**
     * 获取前十名的志愿者信息
     * */
    public List<RankVolunteers> rankVolunteers();
}
