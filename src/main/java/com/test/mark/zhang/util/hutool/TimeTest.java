package com.test.mark.zhang.util.hutool;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.date.Month;
import cn.hutool.core.lang.Console;
import org.junit.Test;

import javax.jws.Oneway;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author by mark
 * @Classname TimeTest
 * @Description TODO
 * @Date 2021/7/19 1:37 下午
 */
public class TimeTest {

    //Date、long、Calendar之间的相互转换
    @Test
    public void test01() {
        //当前时间
        Date date = DateUtil.date();
        //当前时间
        Date date2 = DateUtil.date(Calendar.getInstance());
        //当前时间
        Date date3 = DateUtil.date(System.currentTimeMillis());
        //当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();
        //当前日期字符串，格式：yyyy-MM-dd
        String today = DateUtil.today();

    }

    @Test
    public void parse() {
        String dateStr = "2017-03-01";
        Date date = DateUtil.parse(dateStr);

        String dateStr2 = "2017-03-01";
        Date date2 = DateUtil.parse(dateStr, "yyyy-MM-dd");
    }

    @Test
    public void parse2() {
        String dateStr = "2017-03-01";
        Date date = DateUtil.parse(dateStr);

        //结果 2017/03/01
        String format = DateUtil.format(date, "yyyy/MM/dd");

        //常用格式的格式化，结果：2017-03-01
        String formatDate = DateUtil.formatDate(date);

        //结果：2017-03-01 00:00:00
        String formatDateTime = DateUtil.formatDateTime(date);

        //结果：00:00:00
        String formatTime = DateUtil.formatTime(date);

    }

    @Test
    public void between() {
        String dateStr1 = "2017-03-01 22:33:23";
        Date date1 = DateUtil.parse(dateStr1);

        String dateStr2 = "2017-04-01 23:33:23";
        Date date2 = DateUtil.parse(dateStr2);

        //相差一个月，31天
        long betweenDay = DateUtil.between(date1, date2, DateUnit.DAY);
        System.out.println(betweenDay);
        //先得到时间戳,再根据时间单位得出差值

        //有时候我们希望看到易读的时间差，比如XX天XX小时XX分XX秒，此时使用DateUtil.formatBetween方法：
        //Level.MINUTE表示精确到分
        //String formatBetween = DateUtil.formatBetween(1000007397L, BetweenFormatter.Level.MINUTE);
        String formatBetween = DateUtil.formatBetween(DateUtil.between(date1, date2, DateUnit.MS), BetweenFormatter.Level.MINUTE);
        //输出：31天1小时
        Console.log(formatBetween);

        // "摩羯座"
        String zodiac = DateUtil.getZodiac(Month.JANUARY.getValue(), 19);

        // "狗"
        String chineseZodiac = DateUtil.getChineseZodiac(1994);

        DateTime dateTime = new DateTime("2017-01-05 12:34:23", DatePattern.NORM_DATETIME_FORMAT);

        //年，结果：2017
        int year = dateTime.year();

        //月份，结果：Month.JANUARY
        Month month = dateTime.monthEnum();

        //日，结果：5
        int day = dateTime.dayOfMonth();
    }

    @Test
    public void chineseDate() {
        //通过农历构建
        ChineseDate chineseDate = new ChineseDate(1992,12,14);

        //通过公历构建
        ChineseDate chineseDate2 = new ChineseDate(DateUtil.parseDate("1993-01-06"));


        //通过公历构建
        ChineseDate date = new ChineseDate(DateUtil.parseDate("2020-01-25"));
        // 一月
        date.getChineseMonth();
        // 正月
        date.getChineseMonthName();
        // 初一
        date.getChineseDay();
        // 庚子
        date.getCyclical();
        // 生肖：鼠
        date.getChineseZodiac();
        // 传统节日（部分支持，逗号分隔）：春节
        date.getFestivals();
        // 庚子鼠年 正月初一
        date.toString();
    }

    @Test
    public void java8() {
        String dateStr = "2020-01-23T12:23:56";
        DateTime dt = DateUtil.parse(dateStr);

        // Date对象转换为LocalDateTime
        LocalDateTime of = LocalDateTimeUtil.of(dt);

        // 时间戳转换为LocalDateTime
        of = LocalDateTimeUtil.ofUTC(dt.getTime());

        LocalDateTime start = LocalDateTimeUtil.parse("2019-02-02T00:00:00");
        LocalDateTime end = LocalDateTimeUtil.parse("2020-02-02T00:00:00");

        Duration between = LocalDateTimeUtil.between(start, end);

        // 365
        between.toDays();
    }



}
