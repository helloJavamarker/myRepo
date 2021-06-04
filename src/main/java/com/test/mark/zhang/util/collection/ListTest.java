package com.test.mark.zhang.util.collection;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Classname ListTest
 * @Description list使用的一些坑, 和替代方案
 * @Date 2021/6/2 4:24 下午
 * @Created by mark
 */
public class ListTest {
    public static void main(String[] args) {
        // 一: Arrays.asList不能对生成的list增删
        List<String> names = Arrays.asList("zhang", "san");
        //strings.add("li");
        // UnsupportedOperationException  内部类    idea yyds,竟然带提示
        // 返回的ArrayList不是真正的java.util下的类,而是Arrays的一个内部类,, java.util.Arrays.ArrayList

        // 二: 将数组包装成list后,再修改数组或list都会相互影响
        String[] arr = {"zhang", "san", "li", "si"};
        List<String> arrList = Arrays.asList(arr);
        arr[0] = "张";
        arrList.set(1, "三");
        System.out.println(Arrays.toString(arr));
        System.out.println(arrList);

        // 解决方案一: 套一层  这样就将list和数组分开了,,而且这里的list是真的ArrayList
        List<String> list = new ArrayList<>(Arrays.asList(arr));

        //方案二: 使用 Guava Lists 提供的方法
        List<String> list2 = Lists.newArrayList(arr);

        //三: list里面的sublist也会相互影响
        List<String> list1 =new ArrayList<>();
        list1.add("zhang1");
        list1.add("zhang2");
        list1.add("zhang3");
        list1.add("zhang4");
        List<String> subList = list1.subList(0,2);
        subList.set(0, "张");
        System.out.println(list1);
        System.out.println(subList);
    }
}
