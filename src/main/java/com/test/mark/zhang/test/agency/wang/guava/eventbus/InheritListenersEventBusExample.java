package com.test.mark.zhang.test.agency.wang.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.test.mark.zhang.test.agency.wang.guava.eventbus.listeners.ConcreteListener;

/***************************************
 * @author:Alex Wang
 * @Date:2017/10/18
 * 532500648
 ***************************************/
public class InheritListenersEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new ConcreteListener());
        System.out.println("post the string event");
        eventBus.post("I am string event");
        System.out.println("post the int event");
        eventBus.post(1000);
    }
}
