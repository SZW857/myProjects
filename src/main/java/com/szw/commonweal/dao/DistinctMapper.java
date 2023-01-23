package com.szw.commonweal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szw.commonweal.entity.views.EmailAndTelephone;
import org.springframework.stereotype.Repository;

@Repository
public interface DistinctMapper extends BaseMapper<EmailAndTelephone> {
}
