package com.szw.commonweal.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szw.commonweal.dao.AttendanceMapper;
import com.szw.commonweal.dao.ProjectMapper;
import com.szw.commonweal.entity.Attendance;
import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.service.AttendanceService;
import com.szw.commonweal.utils.DateFormat;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;
    @Autowired
    private ProjectMapper projectMapper;

    /**
     * 志愿者报名参加活动
     * */
    @Transactional
    @Synchronized
    @Override
    public ResultInfo<Object> attendant(Attendance attendance) {
        ResultInfo<Object> res = new ResultInfo<>();
        System.out.println("from Service项目编号"+attendance.getSerialNum());
        Long startDate = attendance.getStartDate();
        Long finishDate = attendance.getFinishDate();
        String userId = attendance.getUserId();
        //时间戳转换为日期
        String s1 = DateFormat.stampToDate(startDate);
        String s2 = DateFormat.stampToDate(finishDate);
        String preDateFormat = null;
        String afterDateFormat = null;
        Long stamp = null;
        Long stamp1 = null;
        //加工日期
        try {
             preDateFormat = DateFormat.preDateFormat(s1);
             afterDateFormat = DateFormat.afterDateFormat(s2);
            //转换时间戳
             stamp = DateFormat.dateToStamp(preDateFormat);
             stamp1 = DateFormat.dateToStamp(afterDateFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("from Service开始时间"+stamp);
        System.out.println("from Service完成时间"+stamp1);
        List<Attendance> conflictCheck = attendanceMapper.conflictCheck(userId, stamp, stamp1);
        System.out.println("我的service冲突检测"+conflictCheck);
        //时间冲突
        if (!conflictCheck.isEmpty()){
            res.setStatus(ResultInfo.FAIL);
            res.setData("时间冲突");
            res.setCode(555);
            return res;
        }else {
            int i = attendanceMapper.insert(attendance);
            if (i ==1){
                res.setStatus(ResultInfo.SUCCESS);
                res.setData("活动项目插入成功");
            }
            else {
                res.setStatus(ResultInfo.FAIL);
                res.setData("已经参加该活动");
            }
            return res;
        }
    }

    /**
     * 志愿者签到
     * */
    @Override
    public ResultInfo<String> signIn(String voucher, int serialNum, String userId,String content) {
        ResultInfo<String> res = new ResultInfo<>();
        int i = attendanceMapper.signIN(voucher, serialNum, userId,content);
        System.out.println("service的结果i"+i);
        if (i==1){
            res.setData("签到成功");
            res.setStatus(ResultInfo.SUCCESS);
        }else {
            res.setData("签到失败");
            res.setStatus(ResultInfo.FAIL);
        }
            return res;
    }

    /**
     * 时间冲突检测
     * */
    @Override
    @Transactional
    public ResultInfo<String> checkConflict(String userId,Long startDate, Long finishDate) {
        ResultInfo<String> res = new ResultInfo<>();
        List list = attendanceMapper.conflictCheck(userId, startDate, finishDate);
        System.out.println("冲突检测的结果"+list);
        if (!list.isEmpty()){
            System.out.println("冲突检测的结果"+list);
            res.setStatus(ResultInfo.FAIL);
            res.setData("报名时间有冲突");
        }else {
            res.setStatus(ResultInfo.SUCCESS);
            System.out.println("冲突检测的结果"+list);
            res.setData("报名无冲突");
        }
        return res;
    }

    /**
     * 志愿者是否已经报名
     * */
    @Override
    @Transactional
    public int isAlreadyEnroll(String serialNum,String userId) {
        QueryWrapper<Attendance> wrapper = new QueryWrapper<>();
        wrapper.select("serial_num","user_id")
                .eq("user_id",userId)
                .eq("serial_num",serialNum);
        List<Attendance> list = attendanceMapper.selectList(wrapper);
        if (!list.isEmpty()){
            int remaining = projectMapper.remaining(serialNum);
            if (remaining==1){
                return 1;
            }else {
                return 0;
            }
        }
       return 0;
    }
}
