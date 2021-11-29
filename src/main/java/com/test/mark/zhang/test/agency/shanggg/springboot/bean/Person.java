package com.test.mark.zhang.test.agency.shanggg.springboot.bean;

import lombok.Data;

/**
 * @author by mark
 * @Classname Person
 * @Description TODO
 * @Date 2021/11/27 8:20 下午
 */
@Data
public class Person {

    private String name;
    private int age;

    /**
     * 这里有组件之间的依赖
     */
    private Pet pet;
}
