package com.test.mark.zhang.test.other.cache;


import com.test.mark.zhang.test.other.cache.em.TimeUnit;

/**
 * 可选数据库下标
 *
 * @param <K>
 * @param <V>
 * @author bailey
 * @version 1.0
 * @date 2017-07-20 14:09
 */
public interface ShardCache<K, V> {
    /**
     * 加入缓存
     */
    default public void put(String shardName, K key, V value) /*throws LzRuntimeException*/ {
        put(shardName, key, value, 0L, null);
    }

    /**
     * 加入缓存，并设定过期时间
     */
    public void put(String shardName, K key, V value, long expiring, TimeUnit timeUnit) /*throws LzRuntimeException*/;

    /**
     * 从缓存删除
     */
    public void remove(String shardName, K key) /*throws LzRuntimeException*/;

    /**
     * 从缓存读取
     */
    public V get(String shardName, K key) /*throws LzRuntimeException*/;

    /**
     * 是否存在
     */
    public boolean exists(String shardName, K key);

    /**
     * 清空缓存
     */
    public void clear(String shardName) /*throws LzRuntimeException*/;
}
