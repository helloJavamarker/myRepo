package com.test.mark.zhang.conf.ck;

import com.test.mark.zhang.cache.caffeine.UserInfoService;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author by mark
 * @Classname JdbcParamConfig
 * @Description TODO
 * @Date 2021/9/7 3:55 下午
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource.click")
public class JdbcParamConfig {
//    private String driverClassName ;
//    private String url ;
//    private Integer initialSize ;
//    private Integer maxActive ;
//    private Integer minIdle ;
//    private Integer maxWait ;

}

