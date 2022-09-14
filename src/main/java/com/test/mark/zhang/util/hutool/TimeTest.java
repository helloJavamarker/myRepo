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

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
        System.out.println(LocalDateTimeUtil.between(LocalDateTime.now(), LocalDateTime.of(2022, 6, 29, 12, 30, 0), ChronoUnit.HOURS));

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
        ChineseDate chineseDate = new ChineseDate(1992, 12, 14);

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
        LocalDateTime end = LocalDateTimeUtil.parse("2018-02-02T00:00:00");

        Duration between = LocalDateTimeUtil.between(start, end);

        // 365
        System.out.println(between.toDays());
    }


    @Test
    public void testPrint() throws InterruptedException {
        LocalDateTime now = LocalDateTime.now();
        final Date date = new Date();

        TimeUnit.SECONDS.sleep(100);
        LocalDateTime now1 = LocalDateTime.now();
        final Date date1 = new Date();
        System.out.println(LocalDateTimeUtil.between(now, now1));
        System.out.println(LocalDateTimeUtil.between(now, now1, ChronoUnit.MILLIS));
        System.out.println(DateUtil.formatBetween(date, date1, BetweenFormatter.Level.MILLISECOND));

    }

    @Test
    public void testEqual() {
        short a = 20000;
        Short b = 20000;
        System.out.println("equals:" + (b.equals(a)));
        System.out.println("==:" + (b == a));


    }


    public static void main(String[] args) {
        Integer a = new Integer(200);
        Integer b = new Integer(200);
        Integer c = 200;
        Integer e = 200;
        int d = 200;

        System.out.println("两个new出来的对象    ==判断" + (a == b));
        System.out.println("两个new出来的对象    equal判断" + a.equals(b));
        System.out.println("new出的对象和用int赋值的Integer   ==判断" + (a == c));
        System.out.println("new出的对象和用int赋值的Integer   equal判断" + (a.equals(c)));
        System.out.println("两个用int赋值的Integer    ==判断" + (c == e));
        System.out.println("两个用int赋值的Integer    equal判断" + (c.equals(e)));
        System.out.println("基本类型和new出的对象   ==判断" + (d == a));
        System.out.println("基本类型和new出的对象   equal判断" + (a.equals(d)));
        System.out.println("基本类型和自动装箱的对象   ==判断" + (d == c));
        System.out.println("基本类型和自动装箱的对象   equal判断" + (c.equals(d)));


        //https://www.jianshu.com/p/9cb9c61b0986

        //常量池。当你定义的Integer在-128~127之间时，Integer的 == 和equal() 返回是一样的，但当Integer 在那个范围之外时，equal 比较的是值，两值相等就返回true，
        // == 就不一样了，比较的是地址，即便你定义的两个 Integer 的值是一样的， == 返回的也还是false。 你可以看下Integer 类的源码，有个内部静态类 IntegerCache

    }

    @Test
    public void testEq() {
        Integer a = new Integer(5);
        Integer b = new Integer(5);
        Integer c = 127;
        Integer d = 127;
        Integer e = 129;
        Integer f = 129;
        int g = 128;
        System.out.println(a == b);       //false，2个不同的Integer对象，“==”会校验Integer地址是否相同
        System.out.println(a.equals(b));   //true，只是校验Integer值是否相同
        System.out.println(c == d);    //true
        System.out.println(e == f);    //false，Integer值不在-128到127之间，会new一个新对象
        System.out.println(f == g);    //true
    }

    @Test
    public void testThread() {
        Short a = new Short("3");
        short b =3;
        System.out.println("main:" + a.equals(b));
        new Thread(()-> {
            System.out.println("thread1:"  + a.equals(b));
            System.out.println("....");
        }).start();
        System.out.println("end");
    }


}
