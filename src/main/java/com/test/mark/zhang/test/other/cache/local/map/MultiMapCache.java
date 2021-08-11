package com.test.mark.zhang.test.other.cache.local.map;

import com.test.mark.zhang.test.other.cache.em.TimeUnit;
import com.test.mark.zhang.test.other.cache.key.CacheKeyTransformer;
import com.test.mark.zhang.test.other.cache.key.LimitedHashCodeTransformer;
import com.test.mark.zhang.test.other.cache.local.MapCache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MultiMapCache extends MapCache {
    protected CacheKeyTransformer kenGenerator;
    protected Map<Integer, Map<Object, Object>> cacheMap;
    //将高速缓存数据置为无效,也就是cpu直接从内存中取数据
    private volatile int size;

    public MultiMapCache() {
        kenGenerator = new LimitedHashCodeTransformer();
        cacheMap = new HashMap<>();
    }

    @Override
    protected void doPut(Object key, Object value, long expiring, TimeUnit timeUnit) /*throws LzRuntimeException*/ {
        Map<Object, Object> subMap = takeSub(kenGenerator.make(key));
        if (!subMap.containsKey(key)) {
            size++;
        }
        subMap.put(key, value);
    }

    @Override
    public boolean exists(Object key) {
        return takeSub(kenGenerator.make(key)).containsKey(key);
    }

    @Override
    protected void doRemove(Object key) /*throws LzRuntimeException*/ {
        if (takeSub(kenGenerator.make(key)).remove(key) != null) {
            size = size > 1 ? size - 1 : size;
        }
    }

    @Override
    protected Object doGet(Object key) /*throws LzRuntimeException*/ {
        return takeSub(kenGenerator.make(key)).get(key);
    }

    private Map<Object, Object> takeSub(Integer key) {
        Map<Object, Object> subMap = cacheMap.get(key);
        if (subMap == null) {
            subMap = new ConcurrentHashMap<>();
            cacheMap.put(key, subMap);
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
        return 0;
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
