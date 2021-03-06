package com.test.mark.zhang.test.agency.wang.guava.test.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.test.mark.zhang.test.agency.wang.guava.cache.Employee;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/***************************************
 * @author:Alex Wang
 * @Date:2017/11/18
 * QQ: 532500648
 * QQ群:463962286
 ***************************************/
public class CacheLoaderTest2 {

    /**
     * TTL->time to live
     * Access time => Write/Update/Read
     *
     * @throws InterruptedException
     */
    @Test
    public void testEvictionByAccessTime() throws InterruptedException {
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
                .expireAfterAccess(2, TimeUnit.SECONDS)
                .build(this.createCacheLoader());
        assertThat(cache.getUnchecked("Alex"), notNullValue());
        assertThat(cache.size(), equalTo(1L));

        TimeUnit.SECONDS.sleep(3);
        assertThat(cache.getIfPresent("Alex"), nullValue());

        assertThat(cache.getUnchecked("Guava"), notNullValue());

        TimeUnit.SECONDS.sleep(1);
        assertThat(cache.getIfPresent("Guava"), notNullValue());
        TimeUnit.SECONDS.sleep(1);
        assertThat(cache.getIfPresent("Guava"), notNullValue());

        TimeUnit.SECONDS.sleep(1);
        assertThat(cache.getIfPresent("Guava"), notNullValue());
    }

    /**
     * Write time => write/update
     */
    @Test
    public void testEvictionByWriteTime() throws InterruptedException {
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .build(this.createCacheLoader());

        assertThat(cache.getUnchecked("Alex"), notNullValue());
        assertThat(cache.size(), equalTo(1L));

        assertThat(cache.getUnchecked("Guava"), notNullValue());

        TimeUnit.SECONDS.sleep(1);
        assertThat(cache.getIfPresent("Guava"), notNullValue());
        TimeUnit.MILLISECONDS.sleep(990);
        assertThat(cache.getIfPresent("Guava"), notNullValue());

        TimeUnit.SECONDS.sleep(1);
        assertThat(cache.getIfPresent("Guava"), nullValue());
    }

    /**
     * Strong/soft/weak/Phantom reference
     */
    @Test
    public void testWeakKey() throws InterruptedException {
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .weakValues()
                .weakKeys()
                .build(this.createCacheLoader());
        assertThat(cache.getUnchecked("Alex"), notNullValue());
        assertThat(cache.getUnchecked("Guava"), notNullValue());

        //active method
        //Thread Active design pattern
        System.gc();

        TimeUnit.MILLISECONDS.sleep(100);
        assertThat(cache.getIfPresent("Alex"), nullValue());
    }

    @Test
    public void testSoftKey() throws InterruptedException {
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .softValues()
                .build(this.createCacheLoader());
        int i = 0;
        for (; ; ) {
            cache.put("Alex" + i, new Employee("Alex" + 1, "Alex" + 1, "Alex" + 1));
            System.out.println("The Employee [" + (i++) + "] is store into cache.");
            TimeUnit.MILLISECONDS.sleep(600);
        }
    }

    private CacheLoader<String, Employee> createCacheLoader() {
        return CacheLoader.from(key -> new Employee(key, key, key));
    }
}
