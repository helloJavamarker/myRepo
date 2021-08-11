package com.test.mark.zhang.test.other.cache.em;

/**
 * 缓存报告的一些参数
 *
 * @author fuli
 * @version 1.0.0
 * @date 2018年9月7日
 */
public enum CacheParams {
    /**
     *
     */
    CACHE_NAME("缓存名"),
    SIZE("使用量"),
    SIZE_CAPACITY("总容量"),
    SIZE_QUANTITY("已使用容量"),
    SIZE_MEMORY("消耗内存"),
    TOTAL_GET("总读取次数"),
    TOTAL_PUT("总放入次数"),
    TOTAL_REMOVE("总移除次数"),
    HIT_RATIO("命中率");
    public final String NAME;

    private CacheParams(String NAME) {
        this.NAME = NAME;
    }
}
