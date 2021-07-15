package com.test.mark.zhang.test.other.project.cascade.anno.reflection;

import lombok.Data;

/**
 * @Classname Reflectiontest2
 * @Description TODO
 * @Date 2021/7/11 9:40 上午
 * @Created by mark
 */
public class Reflectiontest2 {
    public static void main(String[] args) throws ClassNotFoundException {
        Student s1 = new Student();

        //1通过对象获得
        Class<? extends Student> f1 = s1.getClass();
        Class f2 = s1.getClass();
        //2通过forname获得
        Class f3 = Class.forName("com.test.mark.zhang.test.other.project.cascade.anno.reflection.Person");
        //3类名.class
        //Class<? extends Student> aClass = Student.class;
        //Class<Student> f4 = Student.class;
        Class f4 = Student.class;

        //上面获得的类都是同一个

        //4 基本内置类型的包装类豆油一个特Type属性
        //Class<Integer> f5 = Integer.TYPE;
        Class f5 = Integer.TYPE;


        /////////////////////////////////////
        Class<?> superclass = f1.getSuperclass();

    }
}


@Data
class Person {
    public String name;
    //必须修饰为public,否则不能被继承
    private int age;
}

class Student  extends Person{
    public Student() {
        this.name = "student";
        //super.setAge(18);
    }

//    public Student(String name) {
//        this.name = "student";
//    }
}

class Teacher  extends Person{
    public Teacher(String nane) {
        this.name = "teacher";
    }
}