package com.test.mark.zhang.test.other.cache.local.map;

import com.test.mark.zhang.test.other.cache.em.TimeUnit;
import com.test.mark.zhang.test.other.cache.key.CacheKeyTransformer;
import com.test.mark.zhang.test.other.cache.key.LimitedHashCodeTransformer;
import com.test.mark.zhang.test.other.cache.local.MapCache;
import com.test.mark.zhang.test.other.cache.local.SyncLRUMapGenerateAble;

import java.util.HashMap;
import java.util.Map;

public class MultiLRUMapCache extends MapCache implements SyncLRUMapGenerateAble {
    protected CacheKeyTransformer kenGenerator;
    protected Map<Integer, Map<Object, Object>> cacheMap;
    private volatile int size;
    private int maxSize;

    public MultiLRUMapCache() {
        init(0, new LimitedHashCodeTransformer());
    }

    public MultiLRUMapCache(Integer subSize) {
        init(subSize, new LimitedHashCodeTransformer());
    }

    private void init(int maxSize, CacheKeyTransformer kenGenerator) {
        this.maxSize = maxSize;
        this.kenGenerator = kenGenerator;
        cacheMap = new HashMap<>();
    }

    @Override
    protected void doPut(Object key, Object value, long expiring, TimeUnit timeUnit) /*throws LzRuntimeException*/ {
        Map<Object, Object> subMap = takeSub(key);
        if (!subMap.containsKey(key)) {
            size++;
        }
        subMap.put(key, value);
    }

    @Override
    public boolean exists(Object key) {
        return takeSub(key).containsKey(key);
    }

    @Override
    protected void doRemove(Object key) /*throws LzRuntimeException*/ {
        if (takeSub(key).remove(key) != null)
            size = size > 1 ? size - 1 : size;
    }

    @Override
    protected Object doGet(Object key) /*throws LzRuntimeException*/ {
        return takeSub(key).get(key);
    }

    protected Map<Object, Object> takeSub(Object key) {
        Integer subKey = kenGenerator.make(key);
        Map<Object, Object> subMap = cacheMap.get(subKey);
        if (subMap == null) {
            subMap = generateLRUMap(maxSize);
            cacheMap.put(subKey, subMap);
        }
        return subMap;
    }

    @Override
    public void clear() /*throws LzRuntimeException*/ {
        cacheMap.clear();
    }

    public void setKenGenerator(CacheKeyTransformer kenGenerator) {
        this.kenGenerator = kenGenerator;
    }

    public Map<Integer, Map<Object, Object>> values() {
        return cacheMap;
    }

    @Override
    public Map<Object, Object> value() {
        return null;
    }

    @Override
    protected long getCapacity() {
        return maxSize * cacheMap.size();
    }

    @Override
    protected long getQuantity() {
        return size;
    }

    @Override
    protected Object getCacheValueOrSize() {
        return cacheMap;
    }
}
