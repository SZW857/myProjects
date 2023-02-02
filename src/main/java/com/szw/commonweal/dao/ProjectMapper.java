package com.szw.commonweal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szw.commonweal.entity.Project;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * 管理员发布支援项目的
 * */
@Repository
@Mapper
public interface ProjectMapper extends BaseMapper<Project> {
    @Update("update project set remaining =remaining-1 where serial_num = #{serialNum}")
    int remaining(String serialNum);
    @Update("update project set remaining =remaining+1 where serial_num = #{serialNum}")
    int optOut(String serialNum);


}
