package com.rabbit.smart.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by xuzhang on 2017/6/27.
 * 时间处理
 */
public class DateUtil {

    /**
     * 获取当前时间对应的字符串
     *
     * @param pattern 常用的有yyyyMMdd，yyyyMMddHHmmss
     * @return 日期字符串
     */
    public static String getString(String pattern) {
        return getString(pattern, new Date());
    }

    /**
     * 获取时间对应的字符串
     *
     * @param pattern 常用的有yyyyMMdd，yyyyMMddHHmmss
     * @param date    日期
     * @return 日期字符串
     */
    public static String getString(String pattern, Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    /**
     * 获取日期
     *
     * @param times
     * @return
     * @throws ParseException
     */
    public static String getString(long times) throws ParseException {
        return getString("yyyy-MM-dd HH:mm:ss", new Date(times));
    }

    /**
     * 获取日期
     *
     * @param pattern
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getDate(String pattern, String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.parse(date);
    }


    /**
     * 获取当前时间对应的毫秒数
     *
     * @return 毫秒数
     */
    public static long getTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取日期后面N天的日期
     *
     * @param num
     * @return
     */
    public static Date addDay(Integer num) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, num);
        return c.getTime();
    }

    /**
     * 获取日期后面N天的日期
     *
     * @param date
     * @param num
     * @return
     */
    public static Date addDay(Date date, Integer num) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, num);
        return c.getTime();
    }
}
