package com.test.mark.zhang.test.other.cache.core.config;

import com.test.mark.zhang.test.other.cache.annotation.RCache;

public class ConfigDetail {
    public static final String ARG_SPLIT_MARK = ";";
    public static final Boolean DEFAULT_LOCAL_ENABLE = false;
    public static final Boolean DEFAULT_REMOTE_ENABLE = false;

    private boolean enable;
    private String clazz;
    private Object[] args;

    @RCache
    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
