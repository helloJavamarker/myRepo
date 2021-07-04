package com.test.mark.zhang.test.other.project.org;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname FieldTest
 * @Description TODO
 * @Date 2021/7/4 8:00 下午
 * @Created by mark
 */
public class FieldTest {

    private List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        FieldTest fieldTest = new FieldTest();
        System.out.println(fieldTest.addName("zhang1"));
        //list.clear();
        //fieldTest.list.clear();
        System.out.println(fieldTest.addName("zhang2"));
        //fieldTest.list.clear();
        System.out.println(fieldTest.addName("zhang3"));
        //fieldTest.list.clear();
        System.out.println(fieldTest.addName("zhang4"));
        fieldTest.list.clear();
        System.out.println(fieldTest.addName("zhang5"));
        System.out.println(fieldTest.addName2("zhang5"));
        System.out.println(fieldTest.addName2("zhang5"));
        //fieldTest.list.clear();
        //在当前线程下的操作,不clear应该也可以

    }

    private List<String> addName(String name)  {
        list.add(name);
        return list;
    }

    private List<String> addName2(String name)  {
        list.add(name);
        return list;
    }
}
