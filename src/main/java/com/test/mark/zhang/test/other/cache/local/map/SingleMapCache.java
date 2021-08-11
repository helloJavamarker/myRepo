package com.test.mark.zhang.test.other.cache.local.map;


import com.test.mark.zhang.test.other.cache.local.MapCache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于Map的Cache;需手动清理
 *
 * @author bailey
 * @version 1.0
 * @date 2017-06-12 17:18
 */
public class SingleMapCache extends MapCache {
    protected Map<Object, Object> cacheMap;

    public SingleMapCache() {
        cacheMap = new ConcurrentHashMap<>();
    }

    public SingleMapCache(Map<Object, Object> cacheMap) {
        this.cacheMap = new ConcurrentHashMap<>(cacheMap);
    }

    @Override
    public Map<Object, Object> value() {
        return cacheMap;
    }

    @Override
    protected long getCapacity() {
        return 0;
    }

    @Override
    protected long getQuantity() {
        return cacheMap.size();
    }

    @Override
    protected Object getCacheValueOrSize() {
        return cacheMap;
    }


}
