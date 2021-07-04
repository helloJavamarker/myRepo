package com.test.mark.zhang.test.agency.wang.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.test.mark.zhang.test.agency.wang.guava.eventbus.events.Apple;
import com.test.mark.zhang.test.agency.wang.guava.eventbus.events.Fruit;
import com.test.mark.zhang.test.agency.wang.guava.eventbus.listeners.FruitEaterListener;

/***************************************
 * @author:Alex Wang
 * @Date:2017/10/18
 * 532500648
 ***************************************/
public class InheritEventsEventBusExample
{
    public static void main(String[] args)
    {
        final EventBus eventBus = new EventBus();
        eventBus.register(new FruitEaterListener());
        eventBus.post(new Apple("apple"));
        System.out.println("============================");
        eventBus.post(new Fruit("apple"));
    }
}
