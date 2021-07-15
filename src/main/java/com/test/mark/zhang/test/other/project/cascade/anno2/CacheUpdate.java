package com.test.mark.zhang.test.other.project.cascade.anno2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author by mark
 * @Classname CacheUpdate
 * @Description TODO
 * @Date 2021/7/12 4:51 下午
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheUpdate {
    String value();
}
