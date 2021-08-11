package com.test.mark.zhang.test.other.cache;

import com.alibaba.fastjson.JSONObject;
import com.test.mark.zhang.test.other.cache.em.TimeUnit;

/**
 * 缓存接口
 *
 * @author bailey.fu
 * @version 1.0
 * @date Dec 14, 2010
 * @description
 */
public interface Cache<K, V> {

    /**
     * 加入缓存
     */
    public void put(K key, V value) /*throws LzRuntimeException*/;

    /**
     * 加入缓存，并设定过期时间
     */
    public void put(K key, V value, long expiring, TimeUnit timeUnit) /*throws LzRuntimeException*/;

    /**
     * 从缓存删除
     */
    public void remove(K key) /*throws LzRuntimeException*/;

    /**
     * 从缓存读取
     */
    public V get(K key) /*throws LzRuntimeException*/;

    /**
     * 是否存在
     */
    public boolean exists(K key);

    /**
     * 清空缓存
     */
    public void clear() /*throws LzRuntimeException*/;

    /**
     * 是否开启
     */
    public boolean useable();

    /**
     * 缓存使用情况报告
     */
    public JSONObject report();

    public String cacheName();
}
