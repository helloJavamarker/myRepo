package com.test.mark.zhang.test.other.project.cascade.anno;

import org.checkerframework.checker.units.qual.A;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Classname AnnotationTest
 * @Description TODO
 * @Date 2021/7/10 5:31 下午
 * @Created by mark
 */
public class AnnotationTest {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.test.mark.zhang.test.other.project.cascade.anno.AnnotationClient");
        MyAnnotation annotation = clazz.getAnnotation(MyAnnotation.class);  //todo 获得类注解
        System.out.println(annotation);

        for (Annotation clazzAnnotation : clazz.getAnnotations()) {
            System.out.println(clazzAnnotation);
        }
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            boolean isAnno = method.isAnnotationPresent(MyAnnotation.class);
            MyAnnotation annotation1 = method.getAnnotation(MyAnnotation.class);
            System.out.println("=====" + annotation1);
            if (isAnno) {
                System.out.println(method + "==>is anno:" + isAnno);
                System.out.println(method.getName() + "==>is anno:" + isAnno);
            }
            if (isAnno) {
                AnnotationClient client = (AnnotationClient)clazz.newInstance();
                method.invoke(client, "content test");
            }
        }

    }

    @Test
    public void test1() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<AnnotationClient> clazz = AnnotationClient.class;
        AnnotationClient client = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("testAnno1");
        method.invoke(client);
    }

}
