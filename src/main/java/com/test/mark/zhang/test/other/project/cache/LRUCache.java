package com.test.mark.zhang.test.other.project.cache;

/**
 * @author by mark
 * @Classname LRUCache
 * @Description TODO
 * @Date 2021/7/21 9:55 上午
 */
public interface LRUCache<K, V> {

    void put(K k, V v);

    V get(K k);

    void clear();

    void remove(K k);

    int size();
    int limit();
}
