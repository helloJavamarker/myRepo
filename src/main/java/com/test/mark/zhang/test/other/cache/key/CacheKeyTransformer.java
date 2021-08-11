package com.test.mark.zhang.test.other.cache.key;

/**
 * 缓存Key生成
 *
 * @author bailey
 * @version 1.0
 * @date 2017-06-13 09:05
 */
public interface CacheKeyTransformer {
    Integer make(Object original);
}
