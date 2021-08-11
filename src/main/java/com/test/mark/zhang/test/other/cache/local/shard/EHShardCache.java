package com.test.mark.zhang.test.other.cache.local.shard;


import com.test.mark.zhang.test.other.cache.LocalCache;
import com.test.mark.zhang.test.other.cache.ShardCache;
import com.test.mark.zhang.test.other.cache.em.TimeUnit;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.HashMap;
import java.util.Map;

public class EHShardCache extends LocalCache implements ShardCache<Object, Object>/*,SyncBeanCreater*/ {
    private CacheManager cacheManager;
    private Map<String, Cache> cacheMap;

    public EHShardCache() /*throws LzRuntimeException*/ {
        cacheManager = CacheManager.create();
        cacheMap = new HashMap<>();
    }

    public EHShardCache(String configurationFileName) /*throws LzRuntimeException*/ {
        cacheManager = CacheManager.create(getClass().getResource(configurationFileName));
        cacheMap = new HashMap<>();
    }

    @Override
    public void put(String shardName, Object key, Object value, long expiring, TimeUnit timeUnit) /*throws LzRuntimeException*/ {
        putCount++;
        Cache cache = takeCache(shardName);
        Element element = null;
        if (expiring > 0L) {
            int expiringSeconds = timeUnit.toSeconds(expiring);
            element = new Element(key, value, expiringSeconds, expiringSeconds);
        } else {
            element = new Element(key, value);
        }
        try {
            cache.remove(key);
            cache.put(element);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(String shardName, Object key) /*throws LzRuntimeException*/ {
        removeCount++;
        takeCache(shardName).remove(key);
    }

    @Override
    public Object get(String shardName, Object key) /*throws LzRuntimeException*/ {
        getCount++;
        Element element = takeCache(shardName).get(key);
        if (element == null) {
            return null;
        }
        hitCount++;
        return element.getObjectValue();
    }

    @Override
    public boolean exists(String shardName, Object key) {
        return takeCache(shardName).isKeyInCache(key);
    }

    @Override
    public void clear(String shardName) /*throws LzRuntimeException*/ {
        takeCache(shardName).removeAll();
    }

    @Override
    protected long getCapacity() {
        return cacheMap.values().stream().reduce(new Long(0), (x, y) -> x + y.getCacheConfiguration().getMaxEntriesInCache(), (u, v) -> u);
    }

    @Override
    protected long getQuantity() {
        return cacheMap.values().stream().reduce(new Long(0), (x, y) -> x + y.getSize(), (u, v) -> u);
    }

    @Override
    protected Object getCacheValueOrSize() {
        return cacheMap.values().stream().reduce(new Long(0), (x, y) -> x + y.getStatistics().getLocalHeapSizeInBytes(), (u, v) -> u);
    }

    private Cache takeCache(String shardName) {
        //return syncCreate(() -> cacheMap.get(shardName), () -> cacheMap.put(shardName, cacheManager.getCache(shardName)));
        return null;
    }

    @Override
    public boolean exists(Object key) {
        return false;
    }

    @Override
    public void clear() /*throws LzRuntimeException*/ {
    }

    @Override
    protected Object doGet(Object key) /*throws LzRuntimeException*/ {
        return null;
    }

    @Override
    protected void doPut(Object key, Object value, long expiring, TimeUnit timeUnit) /*throws LzRuntimeException*/ {
    }

    @Override
    protected void doRemove(Object key) /*throws LzRuntimeException*/ {
    }


}
