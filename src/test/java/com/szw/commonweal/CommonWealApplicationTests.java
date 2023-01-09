package com.szw.commonweal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.szw.commonweal.dao.UserMapper;
import com.szw.commonweal.entity.Volunteer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CommonWealApplicationTests {
    @Autowired UserMapper userMapper;

    @Test
    void contextLoads() {
        QueryWrapper<Volunteer> wrapper = new QueryWrapper<>();
        wrapper.select("passwd")
                .eq("user_id","szw")
                .eq("passwd","S751225241");
        List<Volunteer> list = userMapper.selectList(wrapper);
        System.out.println(list);
    }

}
//    查询全部用户
//    List<Volunteer> list = userMapper.selectList(null);
//        list.forEach(System.out::println);
//    查询指定用户
//    QueryWrapper<Volunteer> wrapper = new QueryWrapper<>();
//        wrapper.eq("id_card","61011119971006501x");
//                userMapper.selectList(wrapper).forEach(System.out::println);
