package com.test.mark.zhang.test.other.cache.core.config;

/**
 * 缓存配置定义
 *
 * @author fuli
 * @version 1.0.0
 * @date 2018年9月10日
 */
public class CacheConfigBean {
    public static final Boolean DEFAULT_ENABLE = false;

    private String name;
    private boolean enable;
    private ConfigDetail local;
    private ConfigDetail remote;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public ConfigDetail getLocal() {
        return local;
    }

    public ConfigDetail getLocalIfNullCreate() {
        if (local == null) {
            local = new ConfigDetail();
        }
        return local;
    }

    public void setLocal(ConfigDetail local) {
        this.local = local;
    }

    public ConfigDetail getRemote() {
        return remote;
    }

    public ConfigDetail getRemoteIfNullCreate() {
        if (remote == null) {
            remote = new ConfigDetail();
        }
        return remote;
    }

    public void setRemote(ConfigDetail remote) {
        this.remote = remote;
    }
}
