package com.test.mark.zhang.test.other.cache.redis.shard;

import com.test.mark.zhang.test.other.cache.Shard;
import com.test.mark.zhang.test.other.cache.ShardCache;
import com.test.mark.zhang.test.other.cache.em.TimeUnit;
import com.test.mark.zhang.test.other.cache.redis.RedisShard;
import com.test.mark.zhang.test.other.cache.redis.SingleRedisCache;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.Set;
import java.util.function.BiFunction;

/**
 * 可指定数据库下标
 *
 * @author bailey
 * @version 1.0
 * @date 2017-07-20 13:41
 */
public class SingleRedisShardCache extends SingleRedisCache implements ShardCache<Object, Object> {
    private Shard shard;

    public SingleRedisShardCache(RedisTemplate<Object, Object> redisTemplate) {
        super(redisTemplate);
        shard = new RedisShard();
    }

    public SingleRedisShardCache(RedisTemplate<Object, Object> redisTemplate, Shard shard) {
        super(redisTemplate);
        this.shard = shard;
    }

    @Override
    public void put(String shardName, Object key, Object value) /*throws LzRuntimeException*/ {
        put(shardName, key, value, -1, null);
    }

    @Override
    public void put(String shardName, Object key, Object value, long expiring, TimeUnit timeUnit) /*throws LzRuntimeException*/ {
        putCount++;
        int dbIndex = shard.convertToIndex(shardName);
        if (dbIndex == -1) {
            super.put(key, value, expiring, timeUnit);
            return;
        }
        redisTemplate.execute(new RedisCallback<Object>() {
            @SuppressWarnings("unchecked")
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.select(dbIndex);
                byte[] keyBytes = serialize.apply((RedisSerializer<Object>) redisTemplate.getKeySerializer(), key);
                byte[] valueBytes = serialize.apply((RedisSerializer<Object>) redisTemplate.getValueSerializer(), value);
                connection.set(keyBytes, valueBytes);
                if (expiring > 0)
                    connection.pExpire(keyBytes, TimeoutUtils.toMillis(expiring, timeUnit.toConcurrent()));
                return value;
            }
        });
    }

    @Override
    public void remove(String shardName, Object key) /*throws LzRuntimeException*/ {
        removeCount++;
        int dbIndex = shard.convertToIndex(shardName);
        if (dbIndex == -1) {
            super.remove(key);
            return;
        }
        redisTemplate.execute(new RedisCallback<Long>() {
            @SuppressWarnings("unchecked")
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.select(dbIndex);
                return connection.del(serialize.apply((RedisSerializer<Object>) redisTemplate.getKeySerializer(), key));
            }
        });
    }

    @Override
    public Object get(String shardName, Object key) /*throws LzRuntimeException*/ {
        getCount++;
        int dbIndex = shard.convertToIndex(shardName);
        Object value = null;
        if (dbIndex == -1) {
            value = super.get(key);
        } else {
            value = redisTemplate.execute(new RedisCallback<Object>() {
                @SuppressWarnings("unchecked")
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    connection.select(dbIndex);
                    byte[] valueBytes = connection
                            .get(serialize.apply((RedisSerializer<Object>) redisTemplate.getKeySerializer(), key));
                    return redisTemplate.getValueSerializer().deserialize(valueBytes);
                }
            });
        }
        if (value != null) {
            hitCount++;
        }
        return value;
    }

    @Override
    public boolean exists(String shardName, Object key) {
        int dbIndex = shard.convertToIndex(shardName);
        if (dbIndex == -1) {
            return super.exists(key);
        }
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @SuppressWarnings("unchecked")
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.select(dbIndex);
                return connection.exists(serialize.apply((RedisSerializer<Object>) redisTemplate.getKeySerializer(), key));
            }
        });
    }

    @Override
    public void clear(String shardName) /*throws LzRuntimeException*/ {
        int dbIndex = shard.convertToIndex(shardName);
        if (dbIndex == -1) {
            super.clear();
            return;
        }
        redisTemplate.execute(new RedisCallback<Long>() {
            @SuppressWarnings("unchecked")
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.select(dbIndex);
                Set<byte[]> keys = connection.keys(serialize.apply((RedisSerializer<Object>) redisTemplate.getKeySerializer(), "*"));
                return connection.del(keys.toArray(new byte[keys.size()][]));
            }
        });
    }

    private BiFunction<RedisSerializer<Object>, Object, byte[]> serialize = (s, t) -> (t instanceof byte[]) ? (byte[]) t : s.serialize(t);

    @Override
    protected long getCapacity() {
        return 0;
    }

    @Override
    protected long getQuantity() {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long dbSize = 0;
                for (int dbIndex = 0; dbIndex < 16; dbIndex++) {
                    connection.select(dbIndex);
                    dbSize += connection.dbSize();
                }
                return dbSize;
            }
        }).intValue();
    }

    @Override
    protected Object getCacheValueOrSize() {
        return 0;
    }

}