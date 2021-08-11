package com.test.mark.zhang.test.other.cache.local.map.shard;

import com.test.mark.zhang.test.other.cache.ShardCache;
import com.test.mark.zhang.test.other.cache.em.TimeUnit;
import com.test.mark.zhang.test.other.cache.local.map.SingleMapCache;

import java.util.HashMap;
import java.util.Map;

public class SingleMapShardCache extends SingleMapCache implements ShardCache<Object, Object>/*,SyncBeanCreater*/ {
    private Map<String, Map<Object, Object>> cachesMap;

    public SingleMapShardCache(String defaultShardName) {
        cachesMap = new HashMap<>();
        cachesMap.put(defaultShardName, super.cacheMap);
    }

    @Override
    public void put(String shardName, Object key, Object value) /*throws LzRuntimeException*/ {
        putCount++;
        takeCache(shardName).put(key, value);
    }

    @Override
    public void put(String shardName, Object key, Object value, long expiring, TimeUnit timeUnit) /*throws LzRuntimeException*/ {
        putCount++;
        takeCache(shardName).put(key, value);
    }

    @Override
    public void remove(String shardName, Object key) /*throws LzRuntimeException*/ {
        removeCount++;
        takeCache(shardName).remove(key);
    }

    @Override
    public Object get(String shardName, Object key) /*throws LzRuntimeException*/ {
        getCount++;
        Object value = takeCache(shardName).get(key);
        if (value != null) {
            hitCount++;
        }
        return value;
    }

    @Override
    public boolean exists(String shardName, Object key) {
        return takeCache(shardName).containsKey(key);
    }

    @Override
    public void clear(String shardName) /*throws LzRuntimeException*/ {
        takeCache(shardName).clear();
    }

    private Map<Object, Object> takeCache(String shardName) {
        //return syncCreate(() -> cachesMap.get(shardName), () -> cachesMap.put(shardName, new ConcurrentHashMap<>()));
        return null;
    }

    @Override
    protected long getCapacity() {
        return 0;
    }

    @Override
    protected long getQuantity() {
        long quantity = 0;
        for (Map<Object, Object> map : cachesMap.values()) {
            quantity += map.size();
        }
        return quantity;
    }

    @Override
    protected Object getCacheValueOrSize() {
        return cachesMap;
    }
}
