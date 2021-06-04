package com.test.mark.zhang.entity;

import lombok.Data;

@Data
public class Animal {
    private Person person;
    private int age;

    public Animal(Person person) {
        this.person = person;
    }
    private static void hello() {
        System.out.println("hello");
    }
}
