package com.test.mark.zhang.test.other.project.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author by mark
 * @Classname IpSync
 * @Description TODO
 * @Date 2021/7/12 7:17 下午
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface IpSync {
    String source();
    SyncMethodEnum method();
}
