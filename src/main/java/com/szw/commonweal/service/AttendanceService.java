package com.szw.commonweal.service;

import com.szw.commonweal.entity.Attendance;
import com.szw.commonweal.entity.ResultInfo;

public interface AttendanceService {

    /**
     * 志愿者报名参加活动
     * */
    public ResultInfo<Object> attendant(Attendance attendance);

    /**
     * 志愿者是否已经报名
     * */
    public int isAlreadyEnroll(String serialNum,String userId);

    /**
     * 报名冲突检测接口
     * */
    public ResultInfo<String> checkConflict(String userId,Long startDate, Long finishDate);

}
