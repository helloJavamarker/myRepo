package com.example.security.openapi;

/**
 * @author by mark
 * @Classname ApiKeyService
 * @Description TODO
 * @Date 2022/3/29 5:02 下午
 */
public interface ApiKeyService {

    boolean valid(String apiKey);
}
