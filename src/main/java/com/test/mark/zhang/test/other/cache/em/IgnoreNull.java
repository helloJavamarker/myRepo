package com.test.mark.zhang.test.other.cache.em;

/**
 * 是否忽略Null值
 *
 * @author fuli
 * @version 1.0.0
 * @date 2019年9月3日
 */
public enum IgnoreNull {
    /**
     * 等价于TRUE
     */
    DEFAULT,
    /**
     * 忽略Null,从源获取
     */
    TRUE,
    /**
     * 不忽略Null,返回Null
     */
    FALSE;

}
