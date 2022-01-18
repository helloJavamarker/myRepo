package com.test.mark.zhang.test.agency.shengsy.java8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author by mark
 * @Classname StreamTest
 * @Description TODO
 * @Date 2021/12/11 9:55 下午
 */
public class StreamTest {
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("hello", "world", "helloworld");
        List<String> list2 = Arrays.asList("hello1", "world", "helloworld");
        List<String> list3 = Arrays.asList("hello1", "world1", "helloworld1");

        list1.stream().mapToInt(String::length).filter(item -> item == 5).findFirst().ifPresent(System.out::println);
        list1.stream().mapToInt(item -> {
            System.out.println(item);
            return item.length();
        }).filter(item -> item == 5).findFirst().ifPresent(System.out::println);
        // 这里stream执行并不是一步一步的遍历,而是只会遍历一次,一次遍历里面,过滤,findFirst再map   所以map执行的次数并不固定
        //stream执行的顺序并不是先map,再filter,再findFirst    而是先filter,再findFirst,再map
        //stream并不是先把集合中所有的数据逐个操作得到最后结果,而是先对集合中第一个数据进行所有操作,得到结果后再对第二个,第三个数据进行操作


        System.out.println("........");
        list2.stream().mapToInt(item -> {
            System.out.println(item);
            return item.length();
        }).filter(item -> item == 5).findFirst().ifPresent(System.out::println);
        System.out.println("........");
        list3.stream().mapToInt(item -> {
            System.out.println(item);
            return item.length();
        }).filter(item -> item == 5).findFirst().ifPresent(System.out::println);
    }
}
