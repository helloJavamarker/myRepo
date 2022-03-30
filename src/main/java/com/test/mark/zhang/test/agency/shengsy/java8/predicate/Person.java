package com.test.mark.zhang.test.agency.shengsy.java8.predicate;

import ch.qos.logback.core.util.TimeUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.util.NumberUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.Collator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author by mark
 * @Classname Person
 * @Description TODO
 * @Date 2021/12/5 8:01 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String name;
    private int age;


    public static void main(String[] args) {

        System.out.println(LocalDate.now() + " 00:00:00");
        System.out.println(LocalDateTime.now().toString());
//        int a = 4;
//        int b = 16;
//        System.out.println(a/b);
//
//        System.out.println(NumberUtil.div(2, 10));
//        System.out.println(NumberUtil.div(0, 10));
//        System.out.println(NumberUtil.div(2, 0));
        List<Person> list = Arrays.asList(new Person(null, 23), new Person("李四", 23), new Person("王五", 23), new Person("王五", 23), new Person("张三", 23), new Person("王五", 23),
                new Person("王五", 23), new Person("张三", 23),new Person("李四", 23), new Person("王五", 23), new Person("王五", 23));
        //中文排序,对于这个排序字段,如果没有强要求,也能直接使用数据库排序,但是数据库排序没有这个严格
        //select *from t_student order by convert(s_name using gbk);   使用中文数据库排序,不能直接使用,要加一个函数
        //https://www.jianshu.com/p/c88844f485ab
//        list.stream().sorted((o1, o2) -> Collator.getInstance(Locale.CHINESE)
//                .compare(o1.getName(),o2.getName())).collect(Collectors.toList()).forEach(System.out::println);

        Map<Optional<String>, List<Person>> collect = list.stream().filter(person -> person.getName() != null).collect(Collectors.groupingBy(person -> Optional.ofNullable(person.getName())));

        collect.entrySet().stream().sorted(Comparator.comparingInt(entry -> -entry.getValue().size()))/*.limit(2)*/.forEach(System.out::println);
        System.out.println(LocalDate.now().minusDays(1));
    }
}
