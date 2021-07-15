package com.test.mark.zhang.test.agency.wang.guava.test.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/***************************************
 * @author:Alex Wang
 * @Date:2018/1/13
 * QQ: 532500648
 * QQ群:463962286
 ***************************************/
public class CacheLoaderTest4 {

    @Test
    public void testCacheStat() {
        CacheLoader<String, String> loader = CacheLoader.from(String::toUpperCase);
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().maximumSize(5).recordStats().build(loader);
        assertCache(cache);
    }

    @Test
    public void testCacheSpec() {
        String spec = "maximumSize=5,recordStats";
        CacheBuilderSpec builderSpec = CacheBuilderSpec.parse(spec);
        CacheLoader<String, String> loader = CacheLoader.from(String::toUpperCase);
        LoadingCache<String, String> cache = CacheBuilder.from(builderSpec).build(loader);

        assertCache(cache);
    }

    private void assertCache(LoadingCache<String, String> cache) {
        assertThat(cache.getUnchecked("alex"), equalTo("ALEX"));//ALEX
        //得到的CacheStats是final的,不能被修改
        CacheStats stats = cache.stats();
        System.out.println(stats.hashCode());
        assertThat(stats.hitCount(), equalTo(0L));
        assertThat(stats.missCount(), equalTo(1L));

        assertThat(cache.getUnchecked("alex"), equalTo("ALEX"));

        stats = cache.stats();
        System.out.println(stats.hashCode());//两次得到的hashcode是不同的,不能直接使用上次的stats,要重新获取===对象不可变
        assertThat(stats.hitCount(), equalTo(1L));
        assertThat(stats.missCount(), equalTo(1L));

        System.out.println(stats.missRate());
        System.out.println(stats.hitRate());
    }
}
