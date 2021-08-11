package com.test.mark.zhang.test.other.cache.annotation;

import com.test.mark.zhang.test.other.cache.aspect.CacheAspectConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(CacheAspectConfiguration.class)
public @interface EnableCache {
}
