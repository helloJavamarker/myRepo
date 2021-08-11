package com.test.mark.zhang.test.other.cache.local.shard;

import com.alibaba.fastjson.JSONObject;
import com.test.mark.zhang.test.other.cache.LocalCache;
import com.test.mark.zhang.test.other.cache.ShardCache;
import com.test.mark.zhang.test.other.cache.em.TimeUnit;
import com.test.mark.zhang.test.other.cache.local.CaffeineCache;
import com.test.mark.zhang.test.other.cache.util.CaffeineConfigParser;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于Caffeine的分片
 * </p>
 * 分片由HashMap实现
 *
 * @author fuli
 * @version 1.0.0
 * @date 2019年1月3日
 */
public class CaffeineShardCache extends LocalCache implements ShardCache<Object, Object>/*,SyncBeanCreater*/ {
    Map<String, CaffeineCache> cacheMap;
    JSONObject cacheConfig;

    public CaffeineShardCache() {
        cacheMap = new HashMap<>();
    }

    public CaffeineShardCache(String jsonOrFile) {
        cacheMap = new HashMap<>();
        this.cacheConfig = CaffeineConfigParser.parse(jsonOrFile);
    }

    public CaffeineShardCache(JSONObject cacheConfig) {
        cacheMap = new HashMap<>();
        this.cacheConfig = cacheConfig;
    }

    public CaffeineShardCache(int maxSize) {
        cacheMap = new HashMap<>();
        cacheConfig = new JSONObject();
        cacheConfig.put(CaffeineConfigParser.CONFIG_MAX_SIZE, maxSize);
    }

    public CaffeineShardCache(int maxSize, long expiring, TimeUnit timeUnit) {
        cacheMap = new HashMap<>();
        cacheConfig = new JSONObject();
        cacheConfig.put(CaffeineConfigParser.CONFIG_MAX_SIZE, maxSize);
        cacheConfig.put(CaffeineConfigParser.CONFIG_EXPIRING, expiring);
        cacheConfig.put(CaffeineConfigParser.CONFIG_TIMEUNIT, timeUnit);
    }

    public CaffeineShardCache(int maxSize, long expiring, TimeUnit timeUnit, String expiringPolicy) {
        cacheMap = new HashMap<>();
        cacheConfig = new JSONObject();
        cacheConfig.put(CaffeineConfigParser.CONFIG_MAX_SIZE, maxSize);
        cacheConfig.put(CaffeineConfigParser.CONFIG_EXPIRING, expiring);
        cacheConfig.put(CaffeineConfigParser.CONFIG_TIMEUNIT, timeUnit);
        cacheConfig.put(CaffeineConfigParser.CONFIG_EXPIRING_POLICY, expiringPolicy);
    }

    @Override
    public void put(String shardName, Object key, Object value, long expiring, TimeUnit timeUnit)
            /*throws LzRuntimeException*/ {
        putCount++;
        takeCache(shardName).put(key, value, expiring, timeUnit);
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
        return takeCache(shardName).exists(key);
    }

    @Override
    public void clear(String shardName) /*throws LzRuntimeException*/ {
        takeCache(shardName).clear();
    }

    @Override
    protected long getCapacity() {
        return cacheMap.values().stream().reduce(new Long(0), (x, y) -> x + y.maxSize(), (u, v) -> u);
    }

    @Override
    protected long getQuantity() {
        return cacheMap.values().stream().reduce(new Long(0), (x, y) -> x + y.size(), (u, v) -> u);
    }

    @Override
    protected Object getCacheValueOrSize() {
        return cacheMap;
    }

    private CaffeineCache takeCache(String shardName) {
        /*return syncCreate(() -> cacheMap.get(shardName), () -> {
            CaffeineCache cache = null;
            if (cacheConfig == null) {
                cache = new CaffeineCache();
            } else {
                Integer maxSize = Optional.ofNullable(cacheConfig.getInteger(CaffeineConfigParser.CONFIG_MAX_SIZE)).orElse(CaffeineCache.DEFAULT_MAX_SIZE);
                Long expiring = Optional.ofNullable(cacheConfig.getLong(CaffeineConfigParser.CONFIG_EXPIRING)).orElse(0L);
                TimeUnit timeUnit = TimeUnit.valueOf(cacheConfig.getString(CaffeineConfigParser.CONFIG_TIMEUNIT));
                String expiringPolicy = cacheConfig.getString(CaffeineConfigParser.CONFIG_EXPIRING_POLICY);

                JSONObject config = cacheConfig.getJSONObject(shardName);
                if (config != null) {
                    maxSize = Optional.ofNullable(config.getInteger(CaffeineConfigParser.CONFIG_MAX_SIZE)).orElse(maxSize);
                    expiring = Optional.ofNullable(config.getLong(CaffeineConfigParser.CONFIG_EXPIRING)).orElse(expiring);
                    timeUnit = Optional.ofNullable(TimeUnit.valueOf(config.getString(CaffeineConfigParser.CONFIG_TIMEUNIT))).orElse(timeUnit);
                    expiringPolicy = Optional.ofNullable(config.getString(CaffeineConfigParser.CONFIG_EXPIRING_POLICY)).orElse(expiringPolicy);
                }
                maxSize = maxSize < 1 ? CaffeineCache.DEFAULT_MAX_SIZE : maxSize;
                if (expiring > 0 && timeUnit != null) {
                    if (StringUtils.isBlank(expiringPolicy)) {
                        cache = new CaffeineCache(maxSize, expiring, timeUnit);
                    } else {
                        cache = new CaffeineCache(maxSize, expiring, timeUnit, expiringPolicy);
                    }
                } else {
                    cache = new CaffeineCache(maxSize);
                }
            }
            //return cacheMap.put(shardName, cache);
            return null;
        });*/
        return null;
    }

    /**
     * 该实现不设置默认分片
     */
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
