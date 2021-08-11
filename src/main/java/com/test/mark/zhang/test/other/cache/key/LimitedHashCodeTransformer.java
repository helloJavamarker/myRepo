package com.test.mark.zhang.test.other.cache.key;

/**
 * 直接以Object的hashcode作为key
 *
 * @author bailey
 * @version 1.0
 * @date 2017-06-13 14:28
 */
public class LimitedHashCodeTransformer extends LimitedTransformer {
    public LimitedHashCodeTransformer() {
        super();
    }

    public LimitedHashCodeTransformer(int limit) {
        super(limit);
    }

    @Override
    public int assess(Object original) {
        return original == null ? 0 : original.hashCode();
    }
}
