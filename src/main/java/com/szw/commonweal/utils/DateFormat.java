package com.szw.commonweal.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

    public static String preDateFormat(String x) throws ParseException {
        String A = x.substring(0, 13);
        String ZERO = ":00";
            String PRE = A + ZERO + ZERO;
            System.out.println("前置"+PRE);
            return PRE;
    }

    public static String afterDateFormat(String x) throws ParseException {
        String A = x.substring(0, 13);
        String ZERO = ":00";
        String THIRTY = ":30";
            String PRE = A + THIRTY + ZERO;
            System.out.println("后置"+PRE);
            return PRE;
    }

    public static Long dateToStamp(String time1) throws ParseException {
        long epoch = new java.text.SimpleDateFormat("yyyy-mm-dd HH:mm:ss").
                parse(time1).getTime() / 1000;
        System.out.println("from工具类时间戳转日期"+epoch);
        return epoch;
    }

    public static String stampToDate(Long X){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long times = X;
        String str = format.format(times*1000);
        System.out.println(str);
        return str;
    }

    public static Date STRING_TO_DATE(String startDate){
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        try {
            date1 = ft.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sql_date1 = new java.sql.Date(date1.getTime());
        return sql_date1;
    }
}
