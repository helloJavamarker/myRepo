package com.test.mark.zhang.test.other.cache.local.map;


import com.test.mark.zhang.test.other.cache.em.TimeUnit;
import com.test.mark.zhang.test.other.cache.local.AutoCleanAbleMapCache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 基于Map的Cache;key不做hash直接存储
 *
 * @author bailey.fu
 * @version 1.8
 * @date Dec 17, 2010
 * @update 2017-01-03 17:42
 * @description 自定义缓存
 */
public class SingleMapAutoCleanCache extends AutoCleanAbleMapCache {
    protected Map<Object, Entity> cacheMap;

    public SingleMapAutoCleanCache() {
        super();
        init();
    }

    /**
     * 清理间隔(单位：分钟)
     *
     * @param clearInterval
     */
    public SingleMapAutoCleanCache(Integer clearInterval) {
        super(clearInterval);
        init();
    }

    private void init() {
        cacheMap = new ConcurrentHashMap<>();
    }

    @Override
    protected Object doGet(Object key) /*throws LzRuntimeException*/ {
        Entity entity = cacheMap.get(key);
        return entity == null ? null : entity.getElement();
    }

    @Override
    protected void doPut(Object key, Object value, long expiring, TimeUnit timeUnit) /*throws LzRuntimeException*/ {
        if (key != null && value != null) {
            if (expiring > 0) {
                cacheMap.put(key, new Entity(value, timeUnit.toMilliseconds(expiring)));
            } else {
                cacheMap.put(key, new Entity(value));
            }
        }
    }

    @Override
    public boolean exists(Object key) {
        return cacheMap.containsKey(key);
    }

    @Override
    protected void doRemove(Object key) /*throws LzRuntimeException*/ {
        cacheMap.remove(key);
    }

    @Override
    public void clear() /*throws LzRuntimeException*/ {
        cacheMap.clear();
    }

    @Override
    protected void clearExpiring() {
        cacheMap.keySet().parallelStream().filter((key) -> {
            Entity value = cacheMap.get(key);
            return value == null || value.unAble();
        }).collect(Collectors.toList()).forEach(cacheMap::remove);
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
        return cacheMap.size();
    }

    @Override
    protected Object getCacheValueOrSize() {
        return cacheMap;
    }
}
