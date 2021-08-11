package com.test.mark.zhang.test.other.cache.core;

import com.test.mark.zhang.test.other.cache.Cache;
import com.test.mark.zhang.test.other.cache.LocalCache;
import com.test.mark.zhang.test.other.cache.RemoteCache;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存管理器工厂
 *
 * @author fuli
 * @date 2018年9月5日
 */
public class CacheManagerFactory {
    public static final String DEFAULT_CACHE_MANAGER_NAME = "DEFAULT_CACHE_MANAGER";
    private static Map<String, CacheManager> MANAGER_HOLDER = new HashMap<>();
    private String clazz;
    private Map<String, Cache<Object, Object>[]> batchMaterials;
    private Cache<Object, Object>[] singleMaterial;

    public CacheManagerFactory() {
        this.clazz = CacheManager.class.getSimpleName();
    }

    public CacheManagerFactory(String clazz) {
        this.clazz = clazz;
    }

    public void batchCreate() {
        if (singleMaterial != null) {
            this.create((LocalCache) singleMaterial[0], (RemoteCache) singleMaterial[1]);
        }
        if (batchMaterials != null) {
            for (String cacheName : batchMaterials.keySet()) {
                Cache<Object, Object>[] cacheArray = batchMaterials.get(cacheName);
                create(cacheName, (LocalCache) cacheArray[0], (RemoteCache) cacheArray[1]);
            }
        }
    }

    public CacheManager create(LocalCache localCache) {
        return createAndSave(DEFAULT_CACHE_MANAGER_NAME, localCache, null);
    }

    public CacheManager create(String cacheManagerName, LocalCache localCache) {
        return createAndSave(cacheManagerName, localCache, null);
    }

    public CacheManager create(LocalCache localCache, RemoteCache remoteCache) {
        return createAndSave(DEFAULT_CACHE_MANAGER_NAME, localCache, remoteCache);
    }

    public CacheManager create(String cacheManagerName, LocalCache localCache, RemoteCache remoteCache) {
        return createAndSave(cacheManagerName, localCache, remoteCache);
    }

    private CacheManager createAndSave(String cacheManagerName, LocalCache localCache, RemoteCache remoteCache) {
        CacheManager cacheManager = MANAGER_HOLDER.get(cacheManagerName);
        if (cacheManager == null) {
            //CustomCacheManager.class.getSimpleName() == "CustomCacheManager"
            if (clazz.equals(CustomCacheManager.class.getSimpleName())) {
                cacheManager = new CustomCacheManager(cacheManagerName, localCache, remoteCache);
            } else {
                cacheManager = new CacheManager(cacheManagerName, localCache, remoteCache);
            }
            MANAGER_HOLDER.put(cacheManagerName, cacheManager);
        }
        return cacheManager;
    }

    public void setBatchMaterials(Map<String, Cache<Object, Object>[]> batchMaterials) {
        this.batchMaterials = batchMaterials;
    }

    public void setSingleMaterial(Cache<Object, Object>[] singleMaterial) {
        this.singleMaterial = singleMaterial;
    }

    @SuppressWarnings("unchecked")
    public static <T extends CacheManager> T get() {
        return (T) get(DEFAULT_CACHE_MANAGER_NAME);
    }

    @SuppressWarnings("unchecked")
    public static <T extends CacheManager> T get(String cacheManagerName) {
        return (T) MANAGER_HOLDER.get(cacheManagerName);
    }

    public static void shutdown() {
        MANAGER_HOLDER.values().forEach((e) -> e.close());
    }
}
