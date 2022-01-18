package com.test.mark.zhang.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {
    /**
     * ALWAYS // 默认策略，任何情况都执行序列化
     * NON_NULL // 非空      null不会序列化,但是""会序列化
     * NON_ABSENT // null的不会序列化，但如果类型是AtomicReference，依然会被序列化
     * NON_EMPTY // null、集合数组等没有内容、空字符串等，都不会被序列化
     * NON_DEFAULT // 如果字段是默认值，就不会被序列化
     * CUSTOM // 此时要指定valueFilter属性，该属性对应一个类，用来自定义判断被JsonInclude修饰的字段是否序列化
     * USE_DEFAULTS // 当JsonInclude在类和属性上都有时，优先使用属性上的注解，此时如果在序列化的get方法上使用了JsonInclude，并设置为USE_DEFAULTS，就会使用类注解的设置
     */
    public Person(String name, int age, String hobby, long IDCard) {
        this.name = name;
        this.age = age;
        this.hobby = hobby;
        this.IDCard = IDCard;
    }

//public java.lang.String com.test.mark.zhang.entity.Person.test2(java.lang.String,int,com.test.mark.zhang.entity.Person)
    public String test2(String name, int age, Person person) {
        return "";
    }

    private String name;

    private int age;

    private String hobby;
    private String hobbyName;

    private long IDCard;
}
