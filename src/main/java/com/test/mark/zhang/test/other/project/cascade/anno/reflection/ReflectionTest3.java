package com.test.mark.zhang.test.other.project.cascade.anno.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Classname ReflectionTest3
 * @Description TODO
 * @Date 2021/7/11 11:28 上午
 * @Created by mark
 */
public class ReflectionTest3 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException {
        Class c1 = Class.forName("com.test.mark.zhang.test.other.project.cascade.anno.reflection.Person1");

        System.out.println(c1.getName());
        System.out.println(c1.getSimpleName());

        Person1 newPerson = new Person1();
        //new的对象可以通过这种方式来获得包名加类名
        System.out.println(newPerson.getClass().getName());
        System.out.println(newPerson.getClass().getSimpleName());

        Method[] methods = c1.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        System.out.println(".................");
        Method[] declaredMethods = c1.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
            //这里方法都是一样的,所以得到methods和declaredMethod是一样的,除了protected boolean com.test.mark.zhang.test.other.project.cascade.anno.reflection.Person1.canEqual(java.lang.Object)
        }

        System.out.println("=============field=============");

        Field[] declaredFields = c1.getDeclaredFields();
        Method getAge = c1.getDeclaredMethod("getAge");  //也会报错,
        //Method getAgePublic = c1.getMethod("getAge",int.class);//也会报错
        //Method getAge = c1.getDeclaredMethod("getAge",Integer.class);  //也会报错,
        //Method getAge = c1.getDeclaredMethod("getAge",String.class,Integer.class); 报错
        System.out.println("getAge:" + getAge);
        //getAge:public int com.test.mark.zhang.test.other.project.cascade.anno.reflection.Person1.getAge()
        Field name = c1.getDeclaredField("name"/*,String.class*/);
        Field[] fields = c1.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        System.out.println(".......................");
        Field[] declaredFields1 = c1.getDeclaredFields();
        fields = declaredFields1;
        for (Field field : fields) {
            System.out.println(field);
        }
    }
}
