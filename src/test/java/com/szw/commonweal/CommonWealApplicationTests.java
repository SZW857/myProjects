package com.szw.commonweal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.szw.commonweal.dao.MangerMapper;
import com.szw.commonweal.dao.UserMapper;
import com.szw.commonweal.entity.Manager;
import com.szw.commonweal.entity.Volunteer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class CommonWealApplicationTests {
    @Autowired
    private MangerMapper mangerMapper;
    @Test
    List contextLoads() {

        QueryWrapper<Manager> wrapper = new QueryWrapper<>();
        wrapper.select("admin_name","admin_id");
        List<Map<String, Object>> mapList = mangerMapper.selectMaps(wrapper);
        System.out.println(mapList);
//        QueryWrapper<Volunteer> wrapper = new QueryWrapper<>();
//        wrapper.select("passwd")
//                .eq("user_id","szw")
//                .eq("passwd","S751225241");
//        List<Volunteer> list = userMapper.selectList(wrapper);
//        System.out.println(list);
        return mapList;
    }

}
//    查询全部用户
//    List<Volunteer> list = userMapper.selectList(null);
//        list.forEach(System.out::println);
//    查询指定用户
//    QueryWrapper<Volunteer> wrapper = new QueryWrapper<>();
//        wrapper.eq("id_card","61011119971006501x");
//                userMapper.selectList(wrapper).forEach(System.out::println);
