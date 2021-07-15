package com.test.mark.zhang.test.other.project.cascade.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname MyAnnotation
 * @Description TODO
 * @Date 2021/7/10 5:30 下午
 * @Created by mark
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyAnnotation {
    String topic() default "default-topic";
}
