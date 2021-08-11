package com.test.mark.zhang.test.other.cache.local;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.test.mark.zhang.test.other.cache.em.TimeUnit;

import java.util.Map;

/**
 * 基于Caffeine实现,所有内容共享同一过期设置
 *
 * @author fuli
 * @version 1.0.0
 * @date 2019年1月3日
 */
public class CaffeineCache extends MapCache {
    public static final int DEFAULT_MAX_SIZE = 100;
    static final String EXPIRING_POLICY_AFTER_ACCESS = "AA";
    static final String EXPIRING_POLICY_AFTER_WRITE = "AW";

    Cache<Object, Object> cache;
    long maxSize;

    public CaffeineCache() {
        maxSize = DEFAULT_MAX_SIZE;
        cache = Caffeine.newBuilder().weakKeys().weakValues().maximumSize(maxSize).build();
    }

    public CaffeineCache(int maxSize) {
        this.maxSize = maxSize > 1 ? maxSize : DEFAULT_MAX_SIZE;
        cache = Caffeine.newBuilder().weakKeys().weakValues().maximumSize(this.maxSize).build();
    }

    public CaffeineCache(int maxSize, long expiring, TimeUnit timeUnit) {
        init(maxSize, expiring, timeUnit, EXPIRING_POLICY_AFTER_WRITE);
    }

    public CaffeineCache(int maxSize, long expiring, TimeUnit timeUnit, String expiringPolicy) {
        init(maxSize, expiring, timeUnit, expiringPolicy);
    }

    private void init(int maxSize, long expiring, TimeUnit timeUnit, String expiringPolicy) {
        this.maxSize = maxSize > 1 ? maxSize : DEFAULT_MAX_SIZE;
        Caffeine<Object, Object> builder = Caffeine.newBuilder();
        if (EXPIRING_POLICY_AFTER_ACCESS.equals(expiringPolicy)) {
            builder = builder.expireAfterAccess(expiring, timeUnit.toConcurrent());
        } else if (EXPIRING_POLICY_AFTER_WRITE.equals(expiringPolicy)) {
            builder = builder.expireAfterWrite(expiring, timeUnit.toConcurrent());
        } else {
            //LOGGER.warn("[Cache] CaffeineCache can not resolve the value of expiringPolicy '" + expiringPolicy + "' , 'AA' or 'AW' is expected ! It will use reference-based with weak");
            builder.weakKeys().weakValues();
        }
        cache = builder.maximumSize(this.maxSize).build();
    }

    /**
     * 当前缓存的数量
     *
     * @return
     */
    public long size() {
        return getQuantity();
    }

    /**
     * 当前缓存容量
     *
     * @return
     */
    public long maxSize() {
        return getCapacity();
    }

    @Override
    protected Object doGet(Object key) /*throws LzRuntimeException*/ {
        return cache.getIfPresent(key);
    }

    @Override
    protected Map<Object, Object> value() {
        return cache.asMap();
    }

    @Override
    protected long getCapacity() {
        return maxSize;
    }

    @Override
    protected long getQuantity() {
        return cache.estimatedSize();
    }

    @Override
    protected Object getCacheValueOrSize() {
        return cache;
    }
}
