package com.test.mark.zhang.test.other.cache.aspect;

@FunctionalInterface
public interface EnhancingFactory<P extends CacheEnhancer> {
    public P create(Object original);
}
