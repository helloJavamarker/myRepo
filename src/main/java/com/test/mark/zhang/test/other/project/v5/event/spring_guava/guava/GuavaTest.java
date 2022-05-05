package com.test.mark.zhang.test.other.project.v5.event.spring_guava.guava;

import com.google.common.eventbus.EventBus;

public class GuavaTest {
 
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        GeventListener listener = new GeventListener();
        eventBus.register(listener);
 
        eventBus.post(new HelloEvent("hello"));
        eventBus.post(new WorldEvent("world", 23333));
    }
}