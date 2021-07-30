package com.test.mark.zhang.test.other.project.cache;

import cn.hutool.extra.spring.SpringUtil;
import com.test.mark.zhang.test.other.project.security.SecurityZone;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author by mark
 * @Classname SecurityZoneCache
 * @Description TODO
 * @Date 2021/7/20 3:52 下午
 */
public class SecurityZoneCache<T extends SecurityZone> implements CacheLoader<T> {
    private final Map<String, SecurityZone> inner_security_zone;
    private final Map<String, String> inner_id_name_cache;
    //private SecurityZoneController controller = SpringUtil.getBean(SecurityZoneController.class);


    public SecurityZoneCache() {
        this.inner_security_zone = new LinkedHashMap<>();
        this.inner_id_name_cache = new HashMap<>();
    }

    @Override
    public T load() {
        //controller.putCache();
        //return new SecurityZone();
        return null;
    }

    private static class SecurityZoneCacheHolder{
        private static final SecurityZoneCache instance = new SecurityZoneCache();
    }

    public static SecurityZoneCache getInstance() {
        return SecurityZoneCacheHolder.instance;
    }

    public SecurityZone putSecurityZone(String id, SecurityZone securityZone) {
        inner_id_name_cache.put(id, securityZone.getName());
        return inner_security_zone.put(id, securityZone);
    }

    public SecurityZone getInnerZoneByName(String name) {
        for (Map.Entry<String, String> entry : inner_id_name_cache.entrySet()) {
            if (name.equals(entry.getValue())) {
                return getInnerZone(entry.getKey());
            }
        }
        return null;
    }

    private SecurityZone getInnerZone(String id) {
        return inner_security_zone.get(id);
    }

    public boolean clear() {
        inner_security_zone.clear();
        inner_id_name_cache.clear();
        return inner_security_zone.isEmpty();
    }

    @Override
    public String toString() {
        return "SecurityZoneCache{" +
                //"inner_security_zone=" + inner_security_zone +
                "inner_id_name_cache=" + inner_id_name_cache +
                '}';
    }
}
