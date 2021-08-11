package com.test.mark.zhang.test.other.cache.local.map;

import com.test.mark.zhang.test.other.cache.local.MapCache;
import com.test.mark.zhang.test.other.cache.local.SyncLRUMapGenerateAble;
import org.apache.commons.collections.map.LRUMap;

import java.util.Map;

/**
 * 基于LRUMap实现；不支持expiring
 *
 * @author bailey
 * @version 1.0
 * @date 2017-06-26 13:47
 */
public class LRUMapCache extends MapCache implements SyncLRUMapGenerateAble {
    protected Map<Object, Object> cacheMap;

    public LRUMapCache() {
        cacheMap = generateLRUMap();
    }

    public LRUMapCache(Integer size) {
        cacheMap = generateLRUMap(size);
    }

    public LRUMapCache(Map<Object, Object> cacheMap) {
        this.cacheMap = generateLRUMap(cacheMap);
    }

    @Override
    protected Map<Object, Object> value() {
        return cacheMap;
    }

    @Override
    protected long getCapacity() {
        return ((LRUMap) cacheMap).maxSize();
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
