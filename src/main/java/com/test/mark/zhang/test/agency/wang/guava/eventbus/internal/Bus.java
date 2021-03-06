package com.test.mark.zhang.test.agency.wang.guava.eventbus.internal;

/***************************************
 * @author:Alex Wang
 * @Date:2017/10/21
 * 532500648
 ***************************************/
public interface Bus {

    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Object event);

    void post(Object Event, String topic);

    void close();

    String getBusName();
}
