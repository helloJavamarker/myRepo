package com.test.mark.zhang.test.other.project.v5.event.event_bus;

import com.google.common.eventbus.Subscribe;

/**
 * @author by mark
 * @Classname EventListener
 * @Description TODO
 * @Date 2022/4/28 4:33 下午
 */
public class EventListener {

    // 在 Guava EventBus 中，是根据参数类型进行订阅，每个订阅的方法只能由一个参数，同时需要使用 @Subscribe 标识
    /**
     * 监听 Integer 类型的消息
     */
    @Subscribe
    public void listenInteger(Integer param) {
        System.out.println("EventListener#listenInteger ->" + param);
    }

    /**
     * 监听 String 类型的消息
     */
    @Subscribe
    public void listenString(String param) {
        System.out.println("EventListener#listenString ->" + param);
    }
}
