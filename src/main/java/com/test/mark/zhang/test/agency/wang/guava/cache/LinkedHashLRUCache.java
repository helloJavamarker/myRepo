package com.test.mark.zhang.test.agency.wang.guava.cache;

import com.google.common.base.Preconditions;

import java.util.LinkedHashMap;
import java.util.Map;

/***************************************
 * @author:Alex Wang
 * @Date:2017/11/13
 * QQ: 532500648
 * QQ群:463962286
 ***************************************/

/**
 * This class is not the thread-safe class.   线程不安全
 *
 * @param <K>
 * @param <V>
 */
public class LinkedHashLRUCache<K, V> implements LRUCache<K, V> {
    //不推荐LinkedHashLRUCache直接实现LinkedHashMap,而是使用组合
    //直接继承,会导致别的地方在用父类的使用,可以直接看到子类继承的public方法,对外暴露了
    private static class InternalLRUCache<K, V> extends LinkedHashMap<K, V> {
        final private int limit;

        public InternalLRUCache(int limit) {
            //accessOrder 顺序    get后,移到最前
            super(16, 0.75f, true);
            this.limit = limit;
        }

        //逐出策略
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > limit;
        }
    }

    private final int limit;

    private final InternalLRUCache<K, V> internalLRUCache;


    public LinkedHashLRUCache(int limit) {
        //推荐使用这个断言
        Preconditions.checkArgument(limit > 0, "The limit big than zero.");
        this.limit = limit;
        this.internalLRUCache = new InternalLRUCache<>(limit);
    }

    @Override
    public void put(K key, V value) {
        this.internalLRUCache.put(key, value);
    }

    @Override
    public V get(K key) {
        return this.internalLRUCache.get(key);
    }

    @Override
    public void remove(K key) {
        this.internalLRUCache.remove(key);
    }

    @Override
    public int size() {
        return this.internalLRUCache.size();
    }

    @Override
    public void clear() {
        this.internalLRUCache.clear();
    }

    @Override
    public int limit() {
        return this.limit;
    }

    @Override
    public String toString() {
        return internalLRUCache.toString();
    }
}