package com.szw.commonweal;


import com.szw.commonweal.dao.DistinctMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class CommonWealApplicationTests {
   private  String AAA="";
   @Autowired
   private DistinctMapper newsMapper;
   @Test
   public void test() {
      System.out.println(isNumeric(this.AAA));

   }
   public static boolean isNumeric(String str){
      for(int i=str.length();--i>=0;){
         int chr=str.charAt(i);
         if(chr<48 || chr>57)
            return false;
      }
      return true;
   }

}
//    查询全部用户
//    List<Volunteer> list = userMapper.selectList(null);
//        list.forEach(System.out::println);
//    查询指定用户
//    QueryWrapper<Volunteer> wrapper = new QueryWrapper<>();
//        wrapper.eq("id_card","61011119971006501x");
//                userMapper.selectList(wrapper).forEach(System.out::println);
