package com.test.mark.zhang.test.book.effective_java.charper5.genetic;

import com.test.mark.zhang.entity.Person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Classname GenericTest
 * @Description TODO
 * @Date 2021/6/26 9:53 上午
 * @Created by mark
 */
public class GenericTest1 {

    public static void main(String[] args) {
        System.out.println(numElementsInCommon(generateSet1(), generateSet2()));
        System.out.println(numElementsInCommon2(generateSet1(), generateSet2()));
        testArrayAndCollection();
    }

    //返回两个集合中相同元素的个数,没有使用泛型,不推荐
    static int numElementsInCommon(Set s1, Set s2) {
        int result = 0;
        for(Object o1 : s1) {
            if (s2.contains(o1)) {
                result ++;
            }
        }
        return result;
    }

    //推荐使用无限制通配符<?>
    static int numElementsInCommon2(Set<?> s1, Set<?> s2) {
        int result = 0;
        for(Object o1 : s1) {
            if (s2.contains(o1)) {
                result ++;
            }
        }
        return result;
    }

    private static Set<?> generateSet1() {
//        Set<?> s1 = new HashSet<>();
        Set<Object> s1 = new HashSet<>();
        s1.add("zhang");
        s1.add("san");
        s1.add(23);
        return s1;
    }

    private static Set<?> generateSet2() {
        Set<Object> s2 = new HashSet<>();
        s2.add("li");
        s2.add("si");
        s2.add("zhang");
        s2.add(new Person());
        return s2;
    }

    //Set<Object> 是个参数化类型,表示可以包含任何对象类型的一个集合,Set<?>是一个通配符类型,表示只能包含莫一种位置对象类型的一个集合
    //Set是一个原生类型,脱离了泛型系统,要避免使用,不安全
    private static Set<?> generateSet3() {
        Set<?> s2 = new HashSet<>();
//        s2.add("li");
//        s2.add("si");
//        s2.add("zhang");
        //s2.add(new Person());
        return s2;
    }

    //数组和集合
    //泛型擦除,编译时强化类型信息,到运行时丢弃,擦除就是可以使没有使用泛型的代码随意进行互用,确保再java5中平滑过渡到泛型
    private static void testArrayAndCollection() {
        //运行时异常
        Object[] objects = new Long[1];
        objects[0] = "I'm string";

//        List<Object> longs = new ArrayList<Long>();  这里编译通不过
//        longs.add("I'm string");
    }
}
