package com.test.mark.zhang.test.es.project;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by mark
 * @Classname OPLogContext
 * @Description TODO
 * @Date 2021/8/27 4:40 下午
 */
public class OPLogContext<K, V> {

    // static的含义:共享,类加载的时候就会初始化------可以方便在没有创建对象的时候进行调用
    /*static */ThreadLocal<Map<K, V>> local = new ThreadLocal<>();


    public /*static */void put(K key, V value) {
        if (local.get() != null) {
            local.get().put(key, value);
        } else {
            Map<K, V> map = new HashMap<>();
            map.put(key, value);
            local.set(map);
        }
    }

    public /*static*/ V get(K key) {
        if (local.get() == null || !local.get().containsKey(key)) {
            return null;
        }
        return local.get().get(key);
    }

}
