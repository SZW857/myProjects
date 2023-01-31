package com.szw.commonweal;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.deploy.security.ruleset.RuleSetParser;
import com.szw.commonweal.dao.DistinctMapper;

import com.szw.commonweal.dao.RankMapper;
import com.szw.commonweal.dao.VolunteerMapper;
import com.szw.commonweal.entity.Volunteer;
import com.szw.commonweal.entity.views.EnrollResult;
import com.szw.commonweal.entity.views.RankVolunteers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import javax.print.DocFlavor;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@SpringBootTest
class CommonWealApplicationTests {
   @Autowired
    private RankMapper rankMapper;
   @Autowired
   private DistinctMapper newsMapper;
   @Autowired
   private VolunteerMapper volunteer;
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
    @Mapper
    public  void dateToStamp() throws ParseException {

//       volunteer.aaa("szw").forEach(System.out::println);
    }






























































































































































































































































}



