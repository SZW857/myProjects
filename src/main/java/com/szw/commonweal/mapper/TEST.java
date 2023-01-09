//package com.szw.commonweal.controller.mapper;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.szw.commonweal.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class TEST {
//
//    @Autowired
//
//    private UserMapper userMapper;
//    @org.junit.Test
//    public void testpage(){
//        Page<User> page = new Page<>(1,5);
//        userMapper.selectPage(page,null);
//        page.getRecords().forEach(System.out::println);
//    }
//}
