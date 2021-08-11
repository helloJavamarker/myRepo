package com.test.mark.zhang.test.other.cache.local.map.shard;

import com.test.mark.zhang.test.other.cache.ShardCache;
import com.test.mark.zhang.test.other.cache.em.TimeUnit;
import com.test.mark.zhang.test.other.cache.local.map.MultiLRUMapCache;
import org.apache.commons.collections.map.LRUMap;

import java.util.HashMap;
import java.util.Map;

public class MultiLRUMapShardCache extends MultiLRUMapCache implements ShardCache<Object, Object>/*SyncBeanCreater*/ {
    private Map<String, Map<Integer, Map<Object, Object>>> cachesMap;

    public MultiLRUMapShardCache(String defaultShardName) {
        cachesMap = new HashMap<>();
        cachesMap.put(defaultShardName, super.cacheMap);
    }

    public MultiLRUMapShardCache(String defaultShardName, Integer subSize) {
        super(subSize);
        cachesMap = new HashMap<>();
        cachesMap.put(defaultShardName, super.cacheMap);
    }

    @Override
    public void put(String shardName, Object key, Object value) /*throws LzRuntimeException*/ {
        putCount++;
        takeCache(shardName, key).put(key, value);
    }

    @Override
    public void put(String shardName, Object key, Object value, long expiring, TimeUnit timeUnit)
            /*throws LzRuntimeException*/ {
        putCount++;
        takeCache(shardName, key).put(key, value);
    }

    @Override
    public void remove(String shardName, Object key) /*throws LzRuntimeException*/ {
        removeCount++;
        takeCache(shardName, key).remove(key);
    }

    @Override
    public Object get(String shardName, Object key) /*throws LzRuntimeException*/ {
        getCount++;
        Object value = takeCache(shardName, key).get(key);
        if (value != null) {
            hitCount++;
        }
        return value;
    }

    @Override
    public boolean exists(String shardName, Object key) {
        return takeCache(shardName, key).containsKey(key);
    }

    @Override
    public void clear(String shardName) /*throws LzRuntimeException*/ {
        takeCaches(shardName).clear();
    }

    private Map<Integer, Map<Object, Object>> takeCaches(String shardName) {
//        return syncCreate(() -> cachesMap.get(shardName),
//                () -> cachesMap.put(shardName, new HashMap<Integer, Map<Object, Object>>()));
        return null;
    }

    private Map<Object, Object> takeCache(String shardName, Object key) {
        Map<Integer, Map<Object, Object>> cache = /*syncCreate(() -> cachesMap.get(shardName),
                () -> cachesMap.put(shardName, new HashMap<Integer, Map<Object, Object>>()));*/
        new HashMap<>();
        return cache.get(kenGenerator.make(key));
    }

    @Override
    protected long getCapacity() {
        long capacity = 0;
        for (Map<Integer, Map<Object, Object>> maps : cachesMap.values()) {
            for (Map<Object, Object> map : maps.values()) {
                capacity += ((LRUMap) map).maxSize();
            }
        }
        return capacity;
    }

    @Override
    protected long getQuantity() {
        long quantity = 0;
        for (Map<Integer, Map<Object, Object>> maps : cachesMap.values()) {
            for (Map<Object, Object> map : maps.values()) {
                quantity += map.size();
            }
        }
        return quantity;
    }

    @Override
    protected Object getCacheValueOrSize() {
        return cachesMap;
    }
}
