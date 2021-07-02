package com.test.mark.zhang.cache.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * @Classname CaffeineTest
 * @Description TODO
 * @Date 2021/6/29 3:37 下午
 * @Created by mark
 */
public class CaffeineTest {
    private static void test01() {
        // 软引用
        Caffeine.newBuilder().softValues().build();

        // 弱引用
        Caffeine.newBuilder().weakKeys().weakValues().build();

    }
}
