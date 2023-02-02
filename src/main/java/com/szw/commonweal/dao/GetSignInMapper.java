package com.szw.commonweal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szw.commonweal.entity.views.GetSignIn;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Repository
@Mapper
public interface GetSignInMapper extends BaseMapper<GetSignIn> {
}
