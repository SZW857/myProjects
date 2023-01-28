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
    public static final  String ALI_DOMAIN = "https://shizewenlyz.oss-cn-hongkong.aliyuncs.com/";
   @Test
   public void test() {
      //java截取某个字符之前的字符串
         String str = "test_https://www.baidu.com/";
         //截取_之前字符串
         String str1 = str.substring(0, str.indexOf("_"));
         System.out.println("截取_之前字符串:"+str1);

   }
    @Test
    public void t1() {
        String x = null;
        if (x==null){
            System.out.println("等于空");
        }else {
            System.out.println("BUDENGYU");
        }
    }

}

