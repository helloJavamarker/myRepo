package com.test.mark.zhang.test.other.cache;

import com.alibaba.fastjson.JSONObject;
import com.test.mark.zhang.test.other.cache.core.CacheManagerFactory;
import com.test.mark.zhang.test.other.cache.em.CacheParams;
import com.test.mark.zhang.test.other.cache.em.TimeUnit;
import net.sf.ehcache.pool.sizeof.SizeOf;
//import org.ehcache.sizeof.SizeOf;

/**
 * 缓存抽象类
 *
 * @author fuli
 * @version 1.0.0
 * @date 2018年9月18日
 */
public abstract class AbstractCache<K, V> implements Cache<K, V>, CacheLoggerHolder {
    protected String name;
    protected boolean useable;
    protected volatile long getCount;
    protected volatile long hitCount;
    protected volatile long putCount;
    protected volatile long removeCount;
    protected SizeOf sizeOf;

    public AbstractCache() {
        useable = true;
        name = CacheManagerFactory.DEFAULT_CACHE_MANAGER_NAME;
//        sizeOf = SizeOf.newInstance();
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean useable() {
        return useable;
    }

    public void enable() {
        this.useable = true;
    }

    public void disable() {
        this.useable = false;
    }

    @Override
    public String cacheName() {
        return name;
    }

    @Override
    public V get(K key) /*throws LzRuntimeException*/ {
        getCount++;
        V v = doGet(key);
        if (v != null) {
            hitCount++;
        }
        return v;
    }

    @Override
    public void put(K key, V value) /*throws LzRuntimeException*/ {
        put(key, value, 0L, null);
    }

    @Override
    public void put(K key, V value, long expiring, TimeUnit timeUnit) /*throws LzRuntimeException*/ {
        putCount++;
        doPut(key, value, expiring, timeUnit);
    }

    @Override
    public void remove(K key) /*throws LzRuntimeException*/ {
        removeCount++;
        doRemove(key);
    }

    @Override
    public JSONObject report() {
        JSONObject report = new JSONObject();
        report.put(CacheParams.CACHE_NAME.NAME, cacheName());
        JSONObject size = new JSONObject();
        size.put(CacheParams.SIZE_CAPACITY.NAME, getCapacity());
        size.put(CacheParams.SIZE_QUANTITY.NAME, getQuantity());
        long memorySize = 0L;
        Object valueOrSize = getCacheValueOrSize();
        if (valueOrSize instanceof Number) {
            memorySize = ((Number) valueOrSize).longValue();
        } else {
            memorySize = sizeOf.sizeOf(valueOrSize);
        }
        //size.put(CacheParams.SIZE_MEMORY.NAME, NumberFormat.format(String.valueOf(memorySize / 1024), "0.## Kb"));
        report.put(CacheParams.SIZE.NAME, size);
        report.put(CacheParams.TOTAL_GET.NAME, getCount);
        report.put(CacheParams.TOTAL_PUT.NAME, putCount);
        report.put(CacheParams.TOTAL_REMOVE.NAME, removeCount);
        //report.put(CacheParams.HIT_RATIO.NAME, NumberFormat.format(String.valueOf(getCount == 0 ? 1d : (hitCount / getCount)), "##.##%"));
        report.put(CacheParams.HIT_RATIO.NAME, "NumberFormat.format(String.valueOf(getCount == 0 ? 1d : (hitCount / getCount)), )");
        return report;
    }

    public void close() {
    }

    /**
     * 最大容量
     *
     * @return
     */
    protected abstract long getCapacity();

    /**
     * 当前数量
     *
     * @return
     */
    protected abstract long getQuantity();

    /**
     * 当前所有缓存的对象或对象所占空间(由子类实现空间计算)
     *
     * @return
     */
    protected abstract Object getCacheValueOrSize();

    protected abstract V doGet(K key) /*throws LzRuntimeException*/;

    protected abstract void doPut(K key, V value, long expiring, TimeUnit timeUnit) /*throws LzRuntimeException*/;

    protected abstract void doRemove(K key) /*throws LzRuntimeException*/;
}
