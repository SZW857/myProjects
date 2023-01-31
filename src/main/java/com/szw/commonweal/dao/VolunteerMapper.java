package com.szw.commonweal.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szw.commonweal.entity.Volunteer;
import com.szw.commonweal.entity.views.EnrollResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 志愿者
 * */
@Repository
@Mapper
public interface VolunteerMapper extends BaseMapper<Volunteer> {
    /**
     * 获取报名的结果集
     * */
    @Select("select * from enroll_result where user_id=#{userId}")
    List<EnrollResult> getResult(String userId);

}
