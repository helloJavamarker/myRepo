package com.test.mark.zhang.test.other.project.v5.event.spring_guava.spring;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 监听器类，spring也支持一个类中监听多个事件
 */
@Component("springListener")
public class SpringListener {

    /**
     * 监听所有ApplicationEvent类型 及其子类型 的事件
     */
    @EventListener
    public void processApplicationEvent(ApplicationEvent event) {
        System.out.println("process common event, class:" + event.getClass().getSimpleName());
    }

    /**
     * 监听 HelloEvent类型 事件
     */
    @EventListener
    public void processHelloEvent(HelloEvent event) {
        System.out.println("process helloEvent, name:" + event.getEventName());
    }

    /**
     * 监听 CustomerEvent 类型事件，但是需要满足condition条件,即isCustomer=true
     */
    @EventListener(condition = "#event.isCustomer")
    public void processCustomerEvent(CustomerEvent event) {
        System.out.println("process customer CustomerEvent, name:" + event.getName());
    }

    /**
     * 监听 CustomerEvent 类型事件，但是需要满足condition条件,即name="miaomiao"
     */
    @EventListener(condition = "#event.getName().equals('miaomiao')")
    public void processMiaoMiaoEvent(CustomerEvent event) {
        System.out.println("process miaomiao's CustomerEvent, name:" + event.getName());
    }

    /**
     * 支持异步处理事件
     */
    @Async
    @EventListener
    public void processAsyncCustomerEvent(CustomerEvent event) {
        System.out.println("Async process CustomerEvent, name:" + event.getName());
    }

}