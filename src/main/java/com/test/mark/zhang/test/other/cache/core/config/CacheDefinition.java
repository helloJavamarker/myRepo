package com.test.mark.zhang.test.other.cache.core.config;

public class CacheDefinition {
    public static final Boolean DEFAULT_ENABLE = false;

    private String clazz;
    private boolean enable;
    private CacheConfigBean[] multiple;

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public boolean isEnable() {
        return enable;
    }

    public CacheConfigBean[] getMultiple() {
        return multiple;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setMultiple(CacheConfigBean[] multiple) {
        this.multiple = multiple;
    }
}
