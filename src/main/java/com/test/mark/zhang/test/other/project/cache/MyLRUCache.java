package com.test.mark.zhang.test.other.project.cache;


import com.google.common.base.Preconditions;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author by mark
 * @Classname MyCache
 * @Description TODO
 * @Date 2021/7/21 9:51 上午
 */
public class MyLRUCache<K, V> implements LRUCache<K, V>{

    private final InnerLRUCache<K, V> cache;
    private final int limit;

    public MyLRUCache(int limit) {
        Preconditions.checkArgument(limit > 0, "The limit big than zero.");
        this.limit = limit;
        this.cache = new InnerLRUCache<>(limit);
    }

    @Override
    public void put(K k, V v) {
        cache.put(k, v);
    }

    @Override
    public V get(K k) {
        return cache.get(k);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public void remove(K k) {
        cache.remove(k);
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public int limit() {
        return limit;
    }

    private static class InnerLRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int limit;

        private InnerLRUCache(int limit) {
            //accessOrder   false： 基于插入顺序     true：  基于访问顺序，get一个元素后，这个元素被加到最后(使用了LRU 最近最少被使用的调度算法)
            super(32, 0.75F, true);
            this.limit = limit;
        }

        //逐出策略
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > limit;
        }
    }
}
