package com.szw.commonweal.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szw.commonweal.dao.RankMapper;
import com.szw.commonweal.entity.views.RankVolunteers;
import com.szw.commonweal.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RankServiceService implements RankService {
    @Autowired
    private RankMapper rankMapper;

    @Override
    public List<RankVolunteers> rankVolunteers() {
        Page<RankVolunteers> page = new Page<>(1,10);
        Page<RankVolunteers> rankVolunteersPage = rankMapper.selectPage(page, null);
        List<RankVolunteers> list = rankVolunteersPage.getRecords();
        System.out.println(list);
        return list;
    }
}
