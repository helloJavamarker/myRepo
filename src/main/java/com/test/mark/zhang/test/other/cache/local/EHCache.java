package com.test.mark.zhang.test.other.cache.local;

import com.alibaba.fastjson.JSONObject;
import com.test.mark.zhang.test.other.cache.LocalCache;
import com.test.mark.zhang.test.other.cache.em.CacheParams;
import com.test.mark.zhang.test.other.cache.em.TimeUnit;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.statistics.StatisticsGateway;
import org.apache.http.util.Asserts;

import java.io.InputStream;

/**
 * 基于ehcache实现的cache
 *
 * @author bailey.fu
 * @version 1.0
 * @date Dec 16, 2010
 */
public class EHCache extends LocalCache {
    protected CacheManager cacheManager;
    protected net.sf.ehcache.Cache cache;

    /**
     * @param ehcacheName
     * @/*throws LzRuntimeException*/

    public EHCache(String ehcacheName) /*throws LzRuntimeException*/ {
        cacheManager = CacheManager.create();
        init(ehcacheName);
    }

    /**
     * @param classPathConfigFile 基于类路径的配置文件
     * @param ehcacheName
     * @/*throws LzRuntimeException*/

    public EHCache(String classPathConfigFile, String ehcacheName) /*throws LzRuntimeException*/ {
        cacheManager = CacheManager.create(getClass().getResource(classPathConfigFile));
        init(ehcacheName);
    }

    /**
     * @param config
     * @param ehcacheName
     * @/*throws LzRuntimeException*/

    public EHCache(Configuration config, String ehcacheName) /*throws LzRuntimeException*/ {
        cacheManager = CacheManager.create(config);
        init(ehcacheName);
    }

    public EHCache(InputStream inputStream, String ehcacheName) /*throws LzRuntimeException*/ {
        cacheManager = CacheManager.create(inputStream);
        init(ehcacheName);
    }

    private void init(String ehcacheName) /*throws LzRuntimeException*/ {
        try {
            cache = cacheManager.getCache(ehcacheName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Asserts.check(cache != null, "ExceptionCode.FAILED");
    }

    /**
     * 直接操作net.sf.ehcache.Cache
     */
    public net.sf.ehcache.Cache getEhcache() {
        return cache;
    }

    public String getEhcacheName() {
        return cache == null ? null : cache.getName();
    }

    @Override
    public void put(Object key, Object value, long expiring, TimeUnit timeUnit) {

    }

    @Override
    public boolean exists(Object key) {
        return cache.isKeyInCache(key);
    }

    @Override
    public void clear() /*throws LzRuntimeException*/ {
        cache.removeAll();
    }

    @Override
    protected long getCapacity() {
        return cache.getCacheConfiguration().getMaxEntriesInCache();
    }

    @Override
    protected long getQuantity() {
        return cache.getSize();
    }

    @Override
    protected Object getCacheValueOrSize() {
        return cache.getStatistics().getLocalHeapSizeInBytes();
    }

    @Override
    public JSONObject report() {
        JSONObject report = super.report();
        StatisticsGateway statistics = cache.getStatistics();
        report.put(CacheParams.TOTAL_GET.NAME, statistics.cacheGetOperation().count());
        report.put(CacheParams.TOTAL_PUT.NAME, statistics.cachePutAddedCount());
        report.put(CacheParams.TOTAL_REMOVE.NAME, removeCount);
//        report.put(CacheParams.HIT_RATIO.NAME, "NumberFormat.format(String.valueOf(statistics.cacheHitRatio()), "##.##%")");
        report.put(CacheParams.HIT_RATIO.NAME, "NumberFormat.format(String.valueOf(statistics.cacheHitRatio()),)");
        return report;
    }

    @Override
    public void close() {
        cacheManager.shutdown();
        super.close();
    }

    @Override
    protected Object doGet(Object key) /*throws LzRuntimeException*/ {
        Element element = cache.get(key);
        return element == null ? null : element.getObjectValue();
    }

    @Override
    protected void doPut(Object key, Object value, long expiring, TimeUnit timeUnit) {
        Element element = null;
        if (expiring > 0L) {
            int expiringSeconds = timeUnit.toSeconds(expiring);
            element = new Element(key, value, expiringSeconds, expiringSeconds);
        } else {
            element = new Element(key, value);
        }
        try {
            cache.remove(key);
            cache.put(element);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doRemove(Object key) /*throws LzRuntimeException*/ {
        cache.remove(key);
    }
}
