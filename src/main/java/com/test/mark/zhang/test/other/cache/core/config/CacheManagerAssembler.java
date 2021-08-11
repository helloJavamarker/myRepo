package com.test.mark.zhang.test.other.cache.core.config;

import com.test.mark.zhang.test.other.cache.LocalCache;
import com.test.mark.zhang.test.other.cache.RemoteCache;
import com.test.mark.zhang.test.other.cache.core.CacheManager;
import com.test.mark.zhang.test.other.cache.core.CacheManagerFactory;
import com.test.mark.zhang.test.other.cache.em.CacheClass;
import org.apache.http.util.Asserts;
import org.springframework.context.ApplicationContext;

public interface CacheManagerAssembler {
    default void assemble(CacheDefinition cacheDefinition, ApplicationContext applicationContext) {
        CacheManagerFactory cacheManagerFactory = new CacheManagerFactory(cacheDefinition.getClazz());
        boolean totalSwitch = cacheDefinition.isEnable();
        for (CacheConfigBean cacheConfigBean : cacheDefinition.getMultiple()) {
            CacheManager cacheManager = CacheManagerFactory.get(cacheConfigBean.getName());
            Asserts.check(cacheManager == null, "duplicated cacheName:" + cacheConfigBean.getName());

            ConfigDetail localConfig = cacheConfigBean.getLocal();
            LocalCache localCache = null;
            if (localConfig != null) {
                //Enum提供了一个valueOf方法，这个方法和toString方法是相对应的。调用valueOf(“Blue”)将返回 Color.Blue
                //为啥这里返回的不是enum类型的数据
                localCache = CacheClass.valueOf(localConfig.getClazz()).instantiates(localConfig.getArgs(), null);
                if (localConfig.isEnable()) {
                    localCache.enable();
                } else {
                    localCache.disable();
                }
            }
            ConfigDetail remoteConfig = cacheConfigBean.getRemote();
            RemoteCache remoteCache = null;
            if (remoteConfig != null) {
                remoteCache = CacheClass.valueOf(remoteConfig.getClazz()).instantiates(remoteConfig.getArgs(),
                        (beanName) -> {
                            Asserts.check(applicationContext.containsBean(beanName), "the " + cacheConfigBean.getName() + "'s RemoteCache has allocated an instance , but it not be found");
                            return applicationContext.getBean(beanName);
                        });
                if (remoteConfig.isEnable()) {
                    remoteCache.enable();
                } else {
                    remoteCache.disable();
                }
            }
            cacheManager = cacheManagerFactory.create(cacheConfigBean.getName(), localCache, remoteCache);
            if (totalSwitch && cacheConfigBean.isEnable()) {
                cacheManager.enable();
            } else {
                cacheManager.disable();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(CacheClass.valueOf("LRUMapCache"));
    }
}
