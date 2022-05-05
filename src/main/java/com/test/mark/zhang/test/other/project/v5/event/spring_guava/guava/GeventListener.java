package com.test.mark.zhang.test.other.project.v5.event.spring_guava.guava;

import com.google.common.eventbus.Subscribe;

/**
 * @author by mark
 * @Classname GeventListener
 * @Description TODO
 * @Date 2022/4/29 10:51 上午
 */
public class GeventListener {
    /**
     * 监听 HelloEvent 类型及其[父类型]（Object）的事件
     */
    @Subscribe
    public void processEvent(HelloEvent event){
        System.out.println("process hello event, name:" + event.getEventName());
    }

    /**
     * 监听 WorldEvent 类型及其[父类型]（HelloEvent 和 Object）的事件
     * WorldEvent既是HelloEvent,也是object,并且注册也进行了监听,所以父类也会知道
     */
    @Subscribe
    public void processWorldEvent(WorldEvent event) {
        System.out.println("process world eventV1, no:" + event.getEventNo() + ", name:" + event.getEventName());
    }

    /**
     * 注册多个监听器 监听同一事件
     * @param event
     */
    @Subscribe
    public void processWorldEventV2(WorldEvent event) {
        System.out.println("process world eventV2, no:" + event.getEventNo() + ", name:" + event.getEventName());
    }

    @Subscribe
    public void processObject(Object object) {
        System.out.println("process common event, class:" + object.getClass().getSimpleName());
    }
}
