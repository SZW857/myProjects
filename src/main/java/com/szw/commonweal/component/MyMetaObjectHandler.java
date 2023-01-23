package com.szw.commonweal.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("开始自动填充时间...");
        this.setFieldValByName("createTime",new Date(),metaObject);
    }
    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
