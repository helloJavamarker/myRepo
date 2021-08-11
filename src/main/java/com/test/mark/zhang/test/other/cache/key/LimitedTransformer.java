package com.test.mark.zhang.test.other.cache.key;

/**
 * @author bailey
 * @version 1.0
 * @date 2017-06-13 14:37
 */
public abstract class LimitedTransformer implements CacheKeyTransformer {
    private int limit;

    public LimitedTransformer() {
        limit = 100;
    }

    public LimitedTransformer(int limit) {
        this.limit = limit;
    }

    @Override
    public Integer make(Object original) {
        return Math.abs(assess(original)) % limit;
    }

    abstract protected int assess(Object original);
}
