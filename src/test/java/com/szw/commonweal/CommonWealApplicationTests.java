package com.szw.commonweal;


import com.szw.commonweal.dao.NewsMapper;

import com.szw.commonweal.entity.News;

import com.szw.commonweal.utils.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.reflect.Executable;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


@SpringBootTest
class CommonWealApplicationTests {

   @Test
    public void test(){
       System.out.println("hello word");
    }
}

//    查询全部用户
//    List<Volunteer> list = userMapper.selectList(null);
//        list.forEach(System.out::println);
//    查询指定用户
//    QueryWrapper<Volunteer> wrapper = new QueryWrapper<>();
//        wrapper.eq("id_card","61011119971006501x");
//                userMapper.selectList(wrapper).forEach(System.out::println);
