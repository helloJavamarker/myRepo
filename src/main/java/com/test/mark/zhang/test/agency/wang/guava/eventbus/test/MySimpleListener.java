package com.test.mark.zhang.test.agency.wang.guava.eventbus.test;


import com.test.mark.zhang.test.agency.wang.guava.eventbus.internal.MsgType;
import com.test.mark.zhang.test.agency.wang.guava.eventbus.internal.MySubscribe;

/***************************************
 * @author:Alex Wang
 * @Date:2017/10/21
 * 532500648
 ***************************************/
public class MySimpleListener {

    @MySubscribe
    public void test1(String x) {
        System.out.println("MySimpleListener===test1==" + x);
    }

    @MySubscribe(topic = "alex-topic", msgType = MsgType.MSG)
    public void test2(Integer x) throws InterruptedException {
        Thread.sleep(10);
        System.out.println("MySimpleListener===test2==" + x);
    }
}
