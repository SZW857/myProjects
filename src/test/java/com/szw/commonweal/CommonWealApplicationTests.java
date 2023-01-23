package com.szw.commonweal;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szw.commonweal.dao.DistinctMapper;

import com.szw.commonweal.dao.RankMapper;
import com.szw.commonweal.entity.views.RankVolunteers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class CommonWealApplicationTests {
   @Autowired
    private RankMapper rankMapper;
   @Autowired
   private DistinctMapper newsMapper;
   @Test
   public void test() {
//      System.out.println(isNumeric(this.AAA));
      Page<RankVolunteers> page = new Page<>(1,10);
      Page<RankVolunteers> page1 = rankMapper.selectPage(page, null);
      page.getRecords().forEach(System.out::println);
   }
//   public static boolean isNumeric(String str){
//      for(int i=str.length();--i>=0;){
//         int chr=str.charAt(i);
//         if(chr<48 || chr>57)
//            return false;
//      }
//      return true;
//   }

}
//    查询全部用户
//    List<Volunteer> list = userMapper.selectList(null);
//        list.forEach(System.out::println);
//    查询指定用户
//    QueryWrapper<Volunteer> wrapper = new QueryWrapper<>();
//        wrapper.eq("id_card","61011119971006501x");
//                userMapper.selectList(wrapper).forEach(System.out::println);
