package com.test.mark.zhang.test.other.project.cascade.anno.project;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname CacheCollection
 * @Description TODO
 * @Date 2021/7/11 3:27 下午
 * @Created by mark
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheContainer {
    String value();
}
