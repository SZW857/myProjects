package com.szw.commonweal;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.deploy.security.ruleset.RuleSetParser;
import com.szw.commonweal.dao.DistinctMapper;

import com.szw.commonweal.dao.RankMapper;
import com.szw.commonweal.entity.views.RankVolunteers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.print.DocFlavor;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


@SpringBootTest
class CommonWealApplicationTests {
   @Autowired
    private RankMapper rankMapper;
   @Autowired
   private DistinctMapper newsMapper;
    public static final  String ALI_DOMAIN = "https://shizewenlyz.oss-cn-hongkong.aliyuncs.com/";
   @Test
   public void test() throws ParseException {
//      //java截取某个字符之前的字符串
//         String str = "test_https://www.baidu.com/";
//         //截取_之前字符串
//         String str1 = str.substring(0, str.indexOf("_"));
//         System.out.println("截取_之前字符串:"+str1);
       String time1 = "2020-01-19 12:00:00";
//        dateToStamp(time1);
   }
    @Test
    public  void dateToStamp() throws ParseException {
    String x="2023-01-30 22:28:00";

        String A = x.substring(11).substring(0,2);
        int i = Integer.parseInt(A);
        if (i<23){
            //
            System.out.println(1111111);
        }else {
            //23:00
            //>23:00
            System.out.println(2222222);
        }
//        String v = x.substring(11,9);
        System.out.println(A);
//        String ZERO = ":00";
//        String THIRTY = ":30";
//        String PRE = A + THIRTY + ZERO;
//        System.out.println("后置"+PRE);


//
    }






























































































































































































































































}



