package com.test.mark.zhang.test.agency.shengsy.java8.predicate;

import cn.hutool.core.util.NumberUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
        int a = 4;
        int b = 16;
        System.out.println(a/b);

        System.out.println(NumberUtil.div(2, 10));
        System.out.println(NumberUtil.div(0, 10));
        System.out.println(NumberUtil.div(2, 0));
    }
}
