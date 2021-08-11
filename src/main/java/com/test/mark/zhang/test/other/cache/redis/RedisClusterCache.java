package com.test.mark.zhang.test.other.cache.redis;

import com.test.mark.zhang.test.other.cache.RemoteCache;
import com.test.mark.zhang.test.other.cache.em.TimeUnit;

/**
 * 基于Redis集群方式的实现
 *
 * @author bailey
 * @version 1.0
 * @date 2017-11-06 10:42
 */
public class RedisClusterCache extends RemoteCache {
    @Override
    public boolean exists(Object key) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear() /*throws LzRuntimeException*/ {
        // TODO Auto-generated method stub

    }

    @Override
    protected Object doGet(Object key) /*throws LzRuntimeException*/ {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void doPut(Object key, Object value, long expiring, TimeUnit timeUnit) /*throws LzRuntimeException*/ {
        // TODO Auto-generated method stub

    }

    @Override
    protected void doRemove(Object key) /*throws LzRuntimeException*/ {
        // TODO Auto-generated method stub

    }

    @Override
    protected long getCapacity() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    protected long getQuantity() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    protected Object getCacheValueOrSize() {
        // TODO Auto-generated method stub
        return null;
    }

}
