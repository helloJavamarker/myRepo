package com.test.mark.zhang.test.other.cache.local;

import org.apache.commons.collections.map.LRUMap;

import java.util.Collections;
import java.util.Map;

@SuppressWarnings({"unchecked", "rawtypes"})
public interface SyncLRUMapGenerateAble {
    // 默认size为100
    int DEFAULT_SIZE = 100;

    default public Map<Object, Object> generateLRUMap() {
        return Collections.synchronizedMap(new LRUMap(DEFAULT_SIZE));
    }

    default public Map<Object, Object> generateLRUMap(int size) {
        return Collections.synchronizedMap(new LRUMap(size < 1 ? DEFAULT_SIZE : size));
    }

    default public Map<Object, Object> generateLRUMap(Map map) {
        return Collections.synchronizedMap(new LRUMap(map));
    }
}
