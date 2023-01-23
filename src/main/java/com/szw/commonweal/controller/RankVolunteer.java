package com.szw.commonweal.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szw.commonweal.dao.RankMapper;
import com.szw.commonweal.entity.views.RankVolunteers;
import com.szw.commonweal.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@CrossOrigin
public class RankVolunteer  {
    @Autowired
    private RankService rankService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/rankVolunteers")
    public List<RankVolunteers> RankVolunteers(){
        return rankService.rankVolunteers();
    }
}
