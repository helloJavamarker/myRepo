package com.test.mark.zhang.test.other.project.cascade.anno.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Classname ReflectionTest4
 * @Description TODO
 * @Date 2021/7/11 11:58 上午
 * @Created by mark
 */
public class ReflectionTest4 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class c1 = Class.forName("com.test.mark.zhang.test.other.project.cascade.anno.reflection.Person1");
        Person1 o1 = (Person1)c1.newInstance();   //本质调用的是无参构造
        System.out.println(o1);

        Constructor constructor = c1.getDeclaredConstructor(String.class, int.class);
        //Constructor constructor = c1.getDeclaredConstructor(String.class, Integer.class); //NoSuchMethodException
        Person1 zhangsan = (Person1)constructor.newInstance("zhangsan", 23);
        System.out.println(zhangsan);


        o1.setName("lisi");
        System.out.println(o1);

        Person1 o2 = (Person1)c1.newInstance();
        Method setName = c1.getDeclaredMethod("setName", String.class);
        System.out.println(o2);
        setName.invoke(o2, "意大利炮");
        System.out.println(o2);

        //属性
        Person1 o3 = (Person1)c1.newInstance();
        Field name = c1.getDeclaredField("name");
        name.setAccessible(true);  //私有属性,关闭安全监测,但是会降低效率
        name.set(o3, "zhang");
        System.out.println(o3);

    }
}
