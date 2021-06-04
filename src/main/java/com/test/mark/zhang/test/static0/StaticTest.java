package com.test.mark.zhang.test.static0;

import com.test.mark.zhang.entity.Animal;
import com.test.mark.zhang.entity.Person;

/**
 * 执行顺序
 * 1,静态代码块
 * 2,静态方法
 * 3,普通代码块
 * 3,构造方法
 */
public class StaticTest {
    static Person person;
    // 不是静态代码块的话,person没有被赋值,但不会报错
    //{
    static {
        person = new Person("zhang",23,"play",110L);
//        person.setName("zhang");
//        person.setAge(23);
//        person.setHobby("play");
//        person.setIDCard(110L);
        System.out.println("static {}");
    }
    {
        System.out.println("{} init");
    }
    static Animal animal = new Animal(person);

    public static void methodTest() {

    }

    public static void main(String[] args) {
        System.out.println(person);
        System.out.println(animal);

    }
}
