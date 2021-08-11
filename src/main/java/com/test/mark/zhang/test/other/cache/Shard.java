package com.test.mark.zhang.test.other.cache;

/**
 * 分片
 *
 * @author bailey
 * @version 1.0
 * @date 2017-07-28 17:32
 */
public interface Shard {

    <T> T convertToIndex(String shardName);

}
