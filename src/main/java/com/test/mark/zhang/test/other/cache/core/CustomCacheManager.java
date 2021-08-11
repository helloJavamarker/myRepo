package com.test.mark.zhang.test.other.cache.core;

import com.test.mark.zhang.test.other.cache.LocalCache;
import com.test.mark.zhang.test.other.cache.RemoteCache;
import com.test.mark.zhang.test.other.cache.em.TimeUnit;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 可制定put规则
 *
 * @author fuli
 * @version 1.0.0
 * @date 2018年9月10日
 */
@SuppressWarnings("unchecked")
public final class CustomCacheManager extends CacheManager {
    CustomCacheManager(String name, LocalCache localCache, RemoteCache remoteCache) {
        super(name, localCache, remoteCache);
    }

    //fetch Local
    public <T> T fetchLocal(Object key, Function<Object, Object> getOriginal, Predicate<Object> needPut)
            /*throws LzRuntimeException*/ {
        return fetchLocal(key, getOriginal, needPut, 0, null);
    }

    public <T> T fetchLocal(Object key, Function<Object, Object> getOriginal, Predicate<Object> needPut, int expiring,
                            TimeUnit timeUnit) /*throws LzRuntimeException*/ {
        if (!useable || !localCache.useable()) {
            return (T) getOriginal.apply(key);
        }
        Object value = super.getLocal(key);
        if (value == null) {
            value = getOriginal.apply(key);
            if (needPut.test(value)) {
                if (expiring > 0) {
                    super.putToLocal(key, value, expiring, timeUnit);
                } else {
                    super.putToLocal(key, value);
                }
            }
        }
        return (T) value;
    }

    public <T> T fetchLocal(String shardName, Object key, Function<Object, Object> getOriginal,
                            Predicate<Object> needPut) /*throws LzRuntimeException*/ {
        return fetchLocal(shardName, key, getOriginal, needPut, 0, null);
    }

    public <T> T fetchLocal(String shardName, Object key, Function<Object, Object> getOriginal,
                            Predicate<Object> needPut, int expiring, TimeUnit timeUnit) /*throws LzRuntimeException*/ {
        if (!useable || !localCache.useable()) {
            return (T) getOriginal.apply(key);
        }
        Object value = super.getLocal(shardName, key);
        if (value == null) {
            value = getOriginal.apply(key);
            if (needPut.test(value)) {
                if (expiring > 0) {
                    super.putToLocal(shardName, key, value, expiring, timeUnit);
                } else {
                    super.putToLocal(shardName, key, value);
                }
            }
        }
        return (T) value;
    }

    //fetch Remote
    public <T> T fetchRemote(Object key, Function<Object, Object> getOriginal, Predicate<Object> needPut)
            /*throws LzRuntimeException*/ {
        return fetchRemote(key, getOriginal, needPut, 0, null);
    }

    public <T> T fetchRemote(Object key, Function<Object, Object> getOriginal, Predicate<Object> needPut, int expiring,
                             TimeUnit timeUnit) /*throws LzRuntimeException*/ {
        if (!useable || !remoteCache.useable()) {
            return (T) getOriginal.apply(key);
        }
        Object value = super.getLocal(key);
        if (value == null) {
            value = getOriginal.apply(key);
            if (needPut.test(value)) {
                if (expiring > 0) {
                    super.putToRemote(key, value, expiring, timeUnit);
                } else {
                    super.putToRemote(key, value);
                }
            }
        }
        return (T) value;
    }

    public <T> T fetchRemote(String shardName, Object key, Function<Object, Object> getOriginal,
                             Predicate<Object> needPut) /*throws LzRuntimeException*/ {
        return fetchRemote(shardName, key, getOriginal, needPut, 0, null);
    }

    public <T> T fetchRemote(String shardName, Object key, Function<Object, Object> getOriginal,
                             Predicate<Object> needPut, int expiring, TimeUnit timeUnit) /*throws LzRuntimeException*/ {
        if (!useable || !remoteCache.useable()) {
            return (T) getOriginal.apply(key);
        }
        Object value = super.getLocal(shardName, key);
        if (value == null) {
            value = getOriginal.apply(key);
            if (needPut.test(value)) {
                if (expiring > 0) {
                    super.putToRemote(shardName, key, value, expiring, timeUnit);
                } else {
                    super.putToRemote(shardName, key, value);
                }
            }
        }
        return (T) value;
    }
}
