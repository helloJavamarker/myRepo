package com.test.mark.zhang.test.other.project.v5.event.spring_guava.spring;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * 继承 ApplicationEvent 的事件
 */
@Data
public class HelloEvent extends ApplicationEvent {

    private String eventName;

    public HelloEvent(String eventName) {
        super(eventName);
        setEventName(eventName);
    }
}