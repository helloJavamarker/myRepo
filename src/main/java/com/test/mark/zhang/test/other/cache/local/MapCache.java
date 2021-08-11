package com.test.mark.zhang.test.other.cache.local;

import com.test.mark.zhang.test.other.cache.LocalCache;
import com.test.mark.zhang.test.other.cache.em.TimeUnit;

import java.util.Map;

public abstract class MapCache extends LocalCache {

    @Override
    protected void doPut(Object key, Object value, long expiring, TimeUnit timeUnit) /*throws LzRuntimeException*/ {
        value().put(key, value);
    }

    @Override
    protected void doRemove(Object key) /*throws LzRuntimeException*/ {
        value().remove(key);
    }

    @Override
    protected Object doGet(Object key) /*throws LzRuntimeException*/ {
        return value().get(key);
    }

    @Override
    public boolean exists(Object key) {
        return value().containsKey(key);
    }

    @Override
    public void clear() /*throws LzRuntimeException*/ {
        value().clear();
    }

    protected abstract Map<Object, Object> value();
}
