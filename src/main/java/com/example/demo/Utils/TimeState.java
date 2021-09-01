package com.example.demo.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeState {
    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date d=cal.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        String format1 = simpleDateFormat.format(d);
        String substring = format.substring(0, 11);
        String substring1 = format1.substring(0, 11);
        System.out.println(substring+"16:00:00");
        System.out.println(substring1+"16:00:00");

        System.out.println(becomeTimeByLong(1622690488000L));
        System.out.println(dateToStamp("2021-06-02 08:55:09"));
        System.out.println(dateToStamp("2021-06-06 11:54:09"));
    }
    /*
     * 将时间转换为时间戳
     */
    public static Long dateToStamp(String s) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        return ts;
    }
    public static String becomeTimeByLong(Long longTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(longTime);
        //日期格式化
        String res = simpleDateFormat.format(date);
        return res;
    }
}
