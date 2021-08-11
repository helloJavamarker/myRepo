package com.test.mark.zhang.test.other.cache.redis;

import com.test.mark.zhang.test.other.cache.Shard;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author bailey
 * @version 1.0
 * @date 2017-07-28 17:52
 */
public final class RedisShard implements Shard {

    public static final String DEFAULT = "0";
    public static final String INDEX_1 = "1";
    public static final String INDEX_2 = "2";
    public static final String INDEX_3 = "3";
    public static final String INDEX_4 = "4";
    public static final String INDEX_5 = "5";
    public static final String INDEX_6 = "6";
    public static final String INDEX_7 = "7";
    public static final String INDEX_8 = "8";
    public static final String INDEX_9 = "9";
    public static final String INDEX_10 = "10";
    public static final String INDEX_11 = "11";
    public static final String INDEX_12 = "12";
    public static final String INDEX_13 = "13";
    public static final String INDEX_14 = "14";
    public static final String INDEX_15 = "15";

    /**
     * 非法下标转换为-1,将被放入默认库
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T convertToIndex(String shardName) {
        Integer value = NumberUtils.toInt(shardName, -1);
        value = value > -1 && value < 16 ? value : -1;
        return (T) value;
    }
}
