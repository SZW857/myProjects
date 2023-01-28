package com.szw.commonweal.controller;

import com.szw.commonweal.entity.views.RankVolunteers;
import com.szw.commonweal.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class RankVolunteer  {
    @Autowired
    private RankService rankService;

     /**
      *获取前十名好市民
      * */
    @CrossOrigin
    @RequestMapping("/rankVolunteers")
    public List<RankVolunteers> RankVolunteers(){
        return rankService.rankVolunteers();
    }
}
