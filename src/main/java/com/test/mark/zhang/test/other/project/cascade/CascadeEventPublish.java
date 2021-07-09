package com.test.mark.zhang.test.other.project.cascade;

import com.google.common.eventbus.EventBus;

/**
 * @Classname CascadeEventPublish
 * @Description TODO
 * @Date 2021/7/9 2:45 下午
 * @Created by mark
 */
public class CascadeEventPublish {
    static EventBus eventBus = new EventBus();
    public static void post(Object event) {
        eventBus.post(event);
    }
}
