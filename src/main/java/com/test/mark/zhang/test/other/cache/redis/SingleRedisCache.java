package com.test.mark.zhang.test.other.cache.redis;

import com.test.mark.zhang.test.other.cache.RemoteCache;
import com.test.mark.zhang.test.other.cache.em.TimeUnit;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

public class SingleRedisCache extends RemoteCache {
    protected RedisTemplate<Object, Object> redisTemplate;

    public SingleRedisCache(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doRemove(Object key) /*throws LzRuntimeException*/ {
        redisTemplate.delete(key);
    }

    @Override
    public void clear() /*throws LzRuntimeException*/ {
        redisTemplate.delete(redisTemplate.keys("*"));
    }

    @Override
    protected void doPut(Object key, Object value, long expiring, TimeUnit timeUnit)
            /*throws LzRuntimeException*/ {
        if (expiring > 0) {
            BoundValueOperations<Object, Object> boundValue = redisTemplate.boundValueOps(key);
            boundValue.set(value);
            boundValue.expire(expiring, timeUnit.toConcurrent());
        } else {
            redisTemplate.boundValueOps(key).set(value);
        }
    }

    @Override
    protected Object doGet(Object key) {
        BoundValueOperations<Object, Object> boundValue = redisTemplate.boundValueOps(key);
        return boundValue != null ? boundValue.get() : null;
    }

    @Override
    public boolean exists(Object key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    protected long getCapacity() {
        return 0;
    }

    @Override
    protected long getQuantity() {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        }).intValue();
    }

    @Override
    protected Object getCacheValueOrSize() {
        return 0;
    }
}
