package com.test.mark.zhang.test.other.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author by mark
 * @Classname ApplicationPreparedEvent
 * @Description TODO
 * @Date 2021/11/30 1:18 下午
 */
public class ApplicationPreparedEvent extends SpringApplicationEvent {
    private final ConfigurableApplicationContext context;
    public ApplicationPreparedEvent(SpringApplication application, String[] args, ConfigurableApplicationContext context) {
        super(application, args);
        this.context = context;
    }

    public ConfigurableApplicationContext getContext() {
        return this.context;
    }

}
