package com.szw.commonweal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szw.commonweal.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface AttendanceMapper extends BaseMapper<Attendance> {
    @Select("select serial_num ,start_date,finish_date from (select * from attendance  as s where user_id=#{userId}) as b where b.start_date=#{startDate} or (b.finish_date between #{startDate} and #{finishDate}) or (b.start_date between #{startDate} and #{finishDate});")
    List<Attendance> conflictCheck(String userId,Long startDate,Long finishDate);

}
