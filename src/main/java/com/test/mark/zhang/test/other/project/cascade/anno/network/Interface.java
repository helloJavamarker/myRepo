package com.test.mark.zhang.test.other.project.cascade.anno.network;

import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Interface {
    /**
     * 别名数组
     *
     * @return String[]，默认空字符串
     */
    String[] value() default "";
}