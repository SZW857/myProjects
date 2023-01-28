package com.szw.commonweal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szw.commonweal.entity.views.RankVolunteers;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *志愿者排行
 * */
@Repository
@Mapper
public interface RankMapper extends BaseMapper<RankVolunteers> {
}
