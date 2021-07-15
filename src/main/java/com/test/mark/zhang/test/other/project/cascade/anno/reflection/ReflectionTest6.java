package com.test.mark.zhang.test.other.project.cascade.anno.reflection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * @Classname ReflectionTest6
 * @Description TODO
 * @Date 2021/7/11 12:41 下午
 * @Created by mark
 */
public class ReflectionTest6 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class clazz = Class.forName("com.test.mark.zhang.test.other.project.cascade.anno.reflection.Student2");
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        Table table = (Table)clazz.getAnnotation(Table.class);
        System.out.println(table.value());

        Field name = clazz.getDeclaredField("name");
        Annotation[] annotations1 = name.getAnnotations();
        for (Annotation annotation : annotations1) {
            System.out.println(annotation);
        }

        Field name1 = clazz.getDeclaredField("name");
        Cloumn annotation = name1.getAnnotation(Cloumn.class);
        System.out.println(annotation.cloumnName());
        System.out.println(annotation.type());
        System.out.println(annotation.length());
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("zhang")
class Student2{
    @Cloumn(cloumnName = "t_name", type = "varchar")
    private String name;
    @Cloumn(cloumnName = "t_age",type = "int", length = 10)
    private int age;
    @Cloumn(cloumnName = "t_id", type = "varchar")
    private int id;
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Table{
    String value();
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Cloumn {
    String cloumnName();
    String type();
    int length() default 10;
}
