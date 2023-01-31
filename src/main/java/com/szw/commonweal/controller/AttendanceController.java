package com.szw.commonweal.controller;

import com.szw.commonweal.entity.Attendance;
import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enroll")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @CrossOrigin
    @RequestMapping("/attend")
    public ResultInfo<Object> attendanceProject(Attendance attendance){
        return attendanceService.attendant(attendance);
    }

    /**
     * 志愿者是否已经报名
     * */
    @CrossOrigin
    @RequestMapping("/isAlreadyEnroll")
    public int isAlreadyEnroll(@RequestParam("serialNum")String serialNum,@RequestParam("userId")String userId){
        return attendanceService.isAlreadyEnroll(serialNum,userId);
    }

    /**
     * 冲突检测
     * */
    @CrossOrigin
    @RequestMapping("/checkConflict")
    public ResultInfo<String> CHECKCONFILCT(@RequestParam("userId")String userId,@RequestParam("startDate")Long startDate,
                                            @RequestParam("finishDate") Long finishDate){
        return attendanceService.checkConflict(userId,startDate,finishDate);

    }


}
