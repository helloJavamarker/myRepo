package com.test.mark.zhang.test.agency.shengsy.java8.stream;

import com.test.mark.zhang.test.agency.heima.disign.day5.pattern.iterator.Student;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author by mark
 * @Classname StreamTest2
 * @Description TODO
 * @Date 2021/12/11 10:18 下午
 */
public class StreamTest2 {
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("hello", "hi", "你好");
        List<String> list2 = Arrays.asList("zhangsan", "lisi", "wangwu");
        //想要产生一个类似笛卡尔积的形式---或者是矩阵形式  hello zhangsan,hello lisi,hello wangwu,hi zhangsan ......

//        list1.stream().map(item1 -> {
//            for (String item2 : list2) {
//                return item1 + " " + item2;
//            }
//        })
        //这里不能用map,,,,map是将一个item映射为另一个item   但是这里的拓扑结构是将一个映射为一组,所以要用flatMap
        List<String> result = list1.stream().flatMap(item -> list2.stream().map(item2 -> item + "::" + item2)).collect(Collectors.toList());
        System.out.println(result);

        //需求select name,count(*) from t_user group by name
//        list1.stream().collect(Collectors.groupingBy(Student::getName), Collectors.counting())  返回是一个Map<String, Long>
        //collectors还能求平均值

        //分区partitionBy  可以认为是分组的一种特殊形式,只会分为true和false两个组
    }
}
