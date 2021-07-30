package com.test.mark.zhang.test.other.project.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author by mark
 * @Classname CacheUpdate
 * @Description TODO
 * @Date 2021/7/21 9:14 上午
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface CacheUpdate {
    String value();
}
