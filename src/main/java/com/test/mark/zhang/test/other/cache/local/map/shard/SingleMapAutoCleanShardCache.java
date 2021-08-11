package com.test.mark.zhang.test.other.cache.local.map.shard;

import com.test.mark.zhang.test.other.cache.ShardCache;
import com.test.mark.zhang.test.other.cache.em.TimeUnit;
import com.test.mark.zhang.test.other.cache.local.map.SingleMapAutoCleanCache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SingleMapAutoCleanShardCache extends SingleMapAutoCleanCache implements ShardCache<Object, Object>/*,SyncBeanCreater*/ {
    private Map<String, Map<Object, Entity>> cachesMap;

    public SingleMapAutoCleanShardCache(String defaultShardName) {
        cachesMap = new ConcurrentHashMap<>();
        cachesMap.put(defaultShardName, super.cacheMap);
    }

    public SingleMapAutoCleanShardCache(String defaultShardName, Integer clearInterval) {
        super(clearInterval);
        cachesMap = new ConcurrentHashMap<>();
        cachesMap.put(defaultShardName, super.cacheMap);
    }

    @Override
    public void put(String shardName, Object key, Object value) /*throws LzRuntimeException*/ {
        putCount++;
        takeCache(shardName).put(key, new Entity(value));
    }

    @Override
    public void put(String shardName, Object key, Object value, long expiring, TimeUnit timeUnit) /*throws LzRuntimeException*/ {
        putCount++;
        takeCache(shardName).put(key, new Entity(value, timeUnit.toMilliseconds(expiring)));
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
        ;
    }

    @Override
    protected void clearExpiring() {
        cachesMap.values().parallelStream().forEach((cache) -> {
            cache.keySet().parallelStream().filter((key) -> {
                Entity value = cache.get(key);
                return value == null || value.unAble();
            }).collect(Collectors.toList()).forEach(cache::remove);
        });
    }

    private Map<Object, Entity> takeCache(String shardName) {
//        return syncCreate(() -> cachesMap.get(shardName), () -> cachesMap.put(shardName, new ConcurrentHashMap<>()));
        return null;
    }

    @Override
    protected long getCapacity() {
        return 0;
    }

    @Override
    protected long getQuantity() {
        long quantity = 0;
        for (Map<Object, Entity> map : cachesMap.values()) {
            quantity += map.size();
        }
        return quantity;
    }

    @Override
    protected Object getCacheValueOrSize() {
        return super.getCacheValueOrSize();
    }
}
