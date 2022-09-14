package com.test.mark.zhang.test.agency.wang.guava.eventbus.internal;

import java.lang.reflect.Method;

/**
 * @author by mark
 * @Classname EventLoseSubscriber
 * @Description TODO
 * @Date 2022/7/14 1:18 下午
 */
public class EventLoseSubscriber extends MySubscriber{

    public EventLoseSubscriber(Object subscribeObject, Method subscribeMethod) {
        super(subscribeObject, subscribeMethod);
    }
}
