package com.test.mark.zhang.test.agency.wang.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.test.mark.zhang.test.agency.wang.guava.eventbus.listeners.SimpleListener;

/***************************************
 * @author:Alex Wang
 * @Date:2017/10/18
 * 532500648
 ***************************************/
public class SimpleEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new SimpleListener());
//        eventBus.unregister();
        System.out.println("post the simple event.");
        eventBus.post("Simple Event");

        System.out.println("post end");
    }
}
