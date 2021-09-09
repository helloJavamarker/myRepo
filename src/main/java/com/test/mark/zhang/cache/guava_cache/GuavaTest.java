package com.test.mark.zhang.cache.guava_cache;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.test.mark.zhang.entity.Person;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author by mark
 * @Classname GuavaTest
 * @Description TODO
 * @Date 2021/8/24 3:15 下午
 */
@NoArgsConstructor
@Data
public class GuavaTest {
    private Cache<String, List<Person>> container = CacheBuilder.newBuilder().concurrencyLevel(32).softValues()
            .expireAfterWrite(1, TimeUnit.HOURS).initialCapacity(256).maximumSize(2048).build();


    @JsonProperty("name")
    private String name;
    @JsonProperty("age")
    private Integer age;
}
