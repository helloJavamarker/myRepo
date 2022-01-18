package com.test.mark.zhang.test.agency.shengsy.jvm.classloader;

import com.test.mark.zhang.test.agency.shengsy.java8.predicate.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author by mark
 * @Classname MyTest1
 * @Description TODO
 * @Date 2021/12/19 10:48 上午
 */
public class MyTest1 {
    public static void main(String[] args) {

        /**
         * 常量在编译阶段会存入到调用这个常量的方法所在类的常量池中
         * 本质上,调用类并没有直接引用到定义常量的类,因此不会触发定义常量的类的初始化
         *
         * str加上final后,会放到MyTest1这个类的常量池中(之后MyTest1就和MyClass没有任何关系了,甚至可以把MyClass的class文件删除)
         * 所以调用的时候不会导致MyClass初始化
         *
         * java虚拟机初始化一个类时,会先初始化这个类的父类,但是注意,这里对接口不适用:
         *      初始化接口或类时,不会初始化父接口,只有当直接使用这个父接口中的静态方法或字段的时候,才会初始化这个父接口
         */
        //System.out.println(MyClass.str);

        /**
         * 这里会运行代码块的内容
         */
        System.out.println(MyClass2.str);

        List<Person> list = new ArrayList<>();
        list.add(new Person());
        list.add(null);
        list.add(null);
        list.add(new Person());
        list.add(null);
        System.out.println(list);
    }
}

class MyClass {
//    public static final String str = "zhangsan";
    public static String str = "zhangsan";

    static {
        System.out.println("static block");
    }
}

class MyClass2 {
    public static final String str = UUID.randomUUID().toString();
    static {
        System.out.println("static block");
    }
}
