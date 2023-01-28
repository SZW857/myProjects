package com.szw.commonweal.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szw.commonweal.entity.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理员
 * */
@Repository
@Mapper
public interface MangerMapper extends BaseMapper<Manager> {

}
