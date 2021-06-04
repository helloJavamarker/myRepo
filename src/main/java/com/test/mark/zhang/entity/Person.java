package com.test.mark.zhang.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Person {
    public Person(String name, int age, String hobby, long IDCard) {
        this.name = name;
        this.age = age;
        this.hobby = hobby;
        this.IDCard = IDCard;
        System.out.println("construct");
    }

    private String name;

    private int age;

    private String hobby;

    private long IDCard;
}
