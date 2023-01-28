package com.szw.commonweal.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szw.commonweal.entity.Volunteer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 志愿者
 * */
@Repository
@Mapper
public interface VolunteerMapper extends BaseMapper<Volunteer> {

}
