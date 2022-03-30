package com.example.security.openapi;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author by mark
 * @Classname ApiKeyCache
 * @Description TODO
 * @Date 2022/3/30 9:03 上午
 */
@Getter
@AllArgsConstructor
public class ApiKeyCache {
    private final long userId;
    private final String name;
    private final long expireTime;
}
