package com.test.mark.zhang.util.hutool;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ReflectUtil;
import com.test.mark.zhang.util.stream.collect.project.OrgBean;

/**
 * @Classname HutoolTest
 * @Description Hutool是一个Java工具包类库，对文件、流、加密解密、转码、正则、线程、XML等JDK方法进行封装，组成各种Util工具类。
 *              适用于很多项目以及Web开发，并且与其他框架没有耦合性。
 * @Date 2021/7/4 6:38 下午
 * @Created by mark
 * @link https://juejin.cn/post/6844903962265518093
 */
public class HutoolTest {

    private static void testDate() {
        //DateUtil
        //now():String //当前日期时间 yyyy-MM-dd hh:mm:ss
        //today():String //今天日期 yyyy-MM-dd
        //
        //date():DateTime
        //    /*当前时间的DateTime对象（相当于new DateTime()或者new Date()），
        //    此外还提供一个重载方法，传递long类型参数，是给定一个Unix时间戳，
        //    返回这个时间戳的时间。*/
        //lastWeek():DateTime //上周今天（往前7天）
        //lastMonth():DateTime //上个月今天（往前一个月）
        //nextWeek():DateTime //下周今天（往后7天）
        //nextMonth():DateTime //下个月今天（往后一个月）
        //yesterday():DateTime //昨天同时
        //tomorrow():DateTime //明天同时
        //
        //currentSeconds():long //毫秒数
        //
        //thisYear():int //年份
        //thisMonth():int //月份（从0开始）
        //thisWeekOfMonth():int //本月周次(从1开始)
        //thisWeekOfYear():int //本年周次（从1开始）
        //thisDayOfMonth():int //本月第几天（从1开始）
        //thisDayOfWeek():int //本周第几天（从1开始）
        //thisHour(boolean is24HourClock):int //当前小时
        //thisMinute():int //当前分
        //thisSecond():int //当前秒
//yyyy-MM-dd hh:mm:ss
//yyyy-MM-dd
//hh:mm:ss
//yyyy-MM-dd hh:mm
//
//如果你的日期格式不是这几种格式，则需要指定日期格式，对于以上格式还有专门的方法对应：
//    parseDateTime parseDate ParseTime
//
//
//DateUtil.parse()
//DateUtil.parse(String,String) //Date 转换为指定格式的Date对象

        //需要将日期时间格式化输出，Hutool提供了一些方法实现：
        //    DateUtil.formatDateTime(Date date):String //将返回“yyyy-MM-dd hh:mm:ss”格式字符串
        //    DateUtil.formatDate(Date date):String //将返回“yyyy-MM-dd“格式字符串
        //    DateUtil.formatTime(Date date):String //将返回“hh:mm:ss“格式字符串
        //    DateUtil.format(Date,String):String //将返回指定格式的字符串
        //

        ReflectUtil.invoke(new Object(), "getName", "AAA");

    }

    public static void main(String[] args) {
        String name = "zhangsna";
        System.out.println(name.replace("sna", "san"));
    }
}
