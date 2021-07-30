package com.test.mark.zhang.test.other.project.cache;

/**
 * @author by mark
 * @Classname CacheLoader
 * @Description TODO
 * @Date 2021/7/21 10:23 上午
 */
@FunctionalInterface
public interface CacheLoader<T> {
    T load();

    default void refresh() {
        load();
    }
}
