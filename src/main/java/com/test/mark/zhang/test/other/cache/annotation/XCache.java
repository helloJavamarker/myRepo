package com.test.mark.zhang.test.other.cache.annotation;

import com.test.mark.zhang.test.other.cache.core.CacheManagerFactory;
import com.test.mark.zhang.test.other.cache.em.IgnoreNull;
import com.test.mark.zhang.test.other.cache.em.TimeUnit;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mark
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface XCache {
    String cacheName() default CacheManagerFactory.DEFAULT_CACHE_MANAGER_NAME;

    String shardName() default StringUtils.EMPTY;

    String key() default StringUtils.EMPTY;

    String[] remove() default StringUtils.EMPTY;

    /**
     * Spring Expression Language (SpEL) expression used for making the method caching conditional</p>
     * Default is "", meaning the method result is always cached.
     */
    String condition() default StringUtils.EMPTY;

    /**
     * Spring Expression Language (SpEL) expression used to veto method caching.</p>
     * Default is "", meaning that caching is never vetoed.
     */
    String unless() default StringUtils.EMPTY;

    long expiring() default 0;

    TimeUnit timeUnit() default TimeUnit.SECOND;

    String prefix() default StringUtils.EMPTY;

    String suffix() default StringUtils.EMPTY;

    IgnoreNull ignoreNull() default IgnoreNull.DEFAULT;
}
