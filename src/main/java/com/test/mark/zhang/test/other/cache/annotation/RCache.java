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
@Target(ElementType.METHOD)
public @interface RCache {
    /**
     * CacheManager的名字;在多种缓存混合使用时用到
     *
     * @return
     */
    String cacheName() default CacheManagerFactory.DEFAULT_CACHE_MANAGER_NAME;

    /**
     * 数据分库名;由具体实现类解析到对应的数据库下标格式
     *
     * @return
     */
    String shardName() default StringUtils.EMPTY;

    /**
     * 缓存的key；可为空，或字符串，或SpEL表达式
     *
     * @return
     */
    String key() default StringUtils.EMPTY;

    /***
     * 触发从缓存删除的方法<br/>
     * 1.目前只支持同一类中的方法，且remove方法的参数跟key修饰的方法参数一致<br/>
     * 2.remove方法最好不要重复标注在其他方法上，否则容易出错
     * 3.若一个方法既是缓存方法又是remove方法，则忽略remove方法
     *
     * @return
     */
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

    /**
     * 缓存操作是否抛出异常
     *
     * @return
     */
    boolean throwable() default false;

    /**
     * 是否异步执行put/remove
     *
     * @return
     */
    boolean async() default false;

    /**
     * 有效期
     *
     * @return
     */
    long expiring() default 0;

    TimeUnit timeUnit() default TimeUnit.SECOND;

    String prefix() default StringUtils.EMPTY;

    String suffix() default StringUtils.EMPTY;

    IgnoreNull ignoreNull() default IgnoreNull.DEFAULT;
}
