package com.test.mark.zhang.cache.caffeine;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author by mark
 * @Classname UserConfig
 * @Description TODO
 * @Date 2021/9/7 7:56 下午
 */
@Configuration
public class UserConfig {

    @Bean
    public UserInfo userInfo() {
        return new UserInfo();
    }
}
