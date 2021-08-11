package com.test.mark.zhang.test.other.cache.aspect;

import com.test.mark.zhang.test.other.cache.annotation.LCache;
import com.test.mark.zhang.test.other.cache.annotation.RCache;
import com.test.mark.zhang.test.other.cache.annotation.XCache;
import com.test.mark.zhang.test.other.cache.core.CacheManagerFactory;
import com.test.mark.zhang.test.other.cache.em.CacheType;
import com.test.mark.zhang.test.other.cache.em.IgnoreNull;
import com.test.mark.zhang.test.other.cache.em.TimeUnit;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;

public class AnnoBean implements Cloneable {
    CacheType cacheType;
    String cacheName;
    String shardName;
    String key;
    String[] remove;
    String condition;
    String unless;
    boolean throwable;
    boolean async;
    long expiring;
    TimeUnit timeUnit;
    String prefix;
    String suffix;
    IgnoreNull ignoreNull;

    public AnnoBean() {
        cacheName = StringUtils.EMPTY;
        shardName = StringUtils.EMPTY;
        key = StringUtils.EMPTY;
        throwable = false;
        async = false;
        timeUnit = TimeUnit.SECOND;
        prefix = StringUtils.EMPTY;
        suffix = StringUtils.EMPTY;
        ignoreNull = IgnoreNull.DEFAULT;
    }

    boolean isLocal() {
        return cacheType == CacheType.LOCAL;
    }

    boolean isRemote() {
        return cacheType == CacheType.REMOTE;
    }

    public static AnnoBean toAnnoBean(Annotation cacheAnno, final AnnoBean xAnnoBean) {
        AnnoBean ab = null;
        try {
            ab = xAnnoBean == null ? new AnnoBean() : (AnnoBean) xAnnoBean.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        if (cacheAnno instanceof XCache) {
            XCache xCache = (XCache) cacheAnno;
            ab.cacheName = xCache.cacheName();
            ab.shardName = xCache.shardName();
            ab.key = xCache.key();
            ab.remove = xCache.remove();
            ab.condition = xCache.condition();
            ab.unless = xCache.unless();
            ab.expiring = xCache.expiring();
            ab.timeUnit = xCache.timeUnit();
            ab.prefix = xCache.prefix();
            ab.suffix = xCache.suffix();
            ab.ignoreNull = xCache.ignoreNull();
        } else if (cacheAnno instanceof RCache) {
            RCache rCache = (RCache) cacheAnno;
            ab.cacheType = CacheType.REMOTE;
            ab.cacheName = (!StringUtils.isBlank(rCache.cacheName())
                    && !rCache.cacheName().equals(CacheManagerFactory.DEFAULT_CACHE_MANAGER_NAME))
                    ? rCache.cacheName()
                    : ab.cacheName;
            ab.shardName = !StringUtils.isBlank(rCache.shardName()) ? rCache.shardName() : ab.shardName;
            ab.key = !StringUtils.isBlank(rCache.key()) ? rCache.key() : ab.key;
            ab.remove = unique(ab.remove, rCache.remove());
            ab.condition = !StringUtils.isBlank(rCache.condition()) ? rCache.condition() : ab.condition;
            ab.unless = !StringUtils.isBlank(rCache.unless()) ? rCache.unless() : ab.unless;
            ab.throwable = rCache.throwable();
            ab.async = rCache.async();
            if (rCache.expiring() > 0) {
                ab.expiring = rCache.expiring();
                ab.timeUnit = rCache.timeUnit();
            }
            ab.prefix = !StringUtils.isBlank(rCache.prefix()) ? rCache.prefix() : ab.prefix;
            ab.suffix = !StringUtils.isBlank(rCache.suffix()) ? rCache.suffix() : ab.suffix;
            ab.ignoreNull = rCache.ignoreNull();
        } else if (cacheAnno instanceof LCache) {
            LCache lCache = (LCache) cacheAnno;
            ab.cacheType = CacheType.LOCAL;
            ab.cacheName = (!StringUtils.isBlank(lCache.cacheName())
                    && !lCache.cacheName().equals(CacheManagerFactory.DEFAULT_CACHE_MANAGER_NAME))
                    ? lCache.cacheName()
                    : ab.cacheName;
            ab.shardName = !StringUtils.isBlank(lCache.shardName()) ? lCache.shardName() : ab.shardName;
            ab.key = StringUtils.isNotBlank(lCache.key()) ? lCache.key() : ab.key;
            ab.remove = unique(ab.remove, lCache.remove());
            ab.condition = !StringUtils.isBlank(lCache.condition()) ? lCache.condition() : ab.condition;
            ab.unless = !StringUtils.isBlank(lCache.unless()) ? lCache.unless() : ab.unless;
            ab.throwable = lCache.throwable();
            ab.async = lCache.async();
            if (lCache.expiring() > 0) {
                ab.expiring = lCache.expiring();
                ab.timeUnit = lCache.timeUnit();
            }
            ab.prefix = !StringUtils.isBlank(lCache.prefix()) ? lCache.prefix() : ab.prefix;
            ab.suffix = !StringUtils.isBlank(lCache.suffix()) ? lCache.suffix() : ab.suffix;
            ab.ignoreNull = lCache.ignoreNull();
        } else {
            ab = null;
        }
        return ab;
    }

    private static String[] unique(String[] sa1, String[] sa2) {
        if (sa1 == null) {
            return sa2;
        }
        if (sa2 == null) {
            return sa1;
        }
        HashSet<String> hs = new HashSet<>();
        for (String s : sa1) {
            if (StringUtils.isNotBlank(s)) {
                hs.add(s);
            }
        }
        for (String s : sa2) {
            if (StringUtils.isNotBlank(s)) {
                hs.add(s);
            }
        }
        return hs.toArray(new String[hs.size()]);
    }

    @Override
    public String toString() {
        return "AnnoBean [cacheType=" + cacheType + ", cacheName=" + cacheName + ", shardName=" + shardName + ", key="
                + key + ", remove=" + Arrays.toString(remove) + ", condition=" + condition + ", unless=" + unless
                + ", throwable=" + throwable + ", async=" + async + ", expiring=" + expiring + ", timeUnit=" + timeUnit
                + ", prefix=" + prefix + ", suffix=" + suffix + "]";
    }
}
