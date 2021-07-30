package com.test.mark.zhang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
public class Person {
    public Person(String name, int age, String hobby, long IDCard) {
        this.name = name;
        this.age = age;
        this.hobby = hobby;
        this.IDCard = IDCard;
    }
private String test(String name, int age, Person person) {
        return "";
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
