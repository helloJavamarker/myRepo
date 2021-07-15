package com.test.mark.zhang.test.other.project.cascade.anno.kuang;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname MyAnnoTest
 * @Description TODO
 * @Date 2021/7/11 9:02 上午
 * @Created by mark
 */
public class MyAnnoTest {

    @Annotation1(name = "zhangsan")
    public void test01() {

    }
    @Annotation2("value1")
    public void test02() {

    }

}

//RetentionPolicy,ElementType,枚举
//Retention一般使用runtime,,,runtime>class>source
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Annotation1{
    String name();
    int age() default 18;
    String[] hobby() default {};
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Annotation2 {
    //只有一个参数的时候,可以使用value,使用的时候可以省略value
    String value();
}
