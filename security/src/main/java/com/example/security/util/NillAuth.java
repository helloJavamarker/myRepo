package com.example.security.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author by mark
 * @Classname NillAuth
 * @Description 白名单鉴权,不用登录就可以访问
 * @Date 2021/11/18 10:23 上午
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface NillAuth {
}
