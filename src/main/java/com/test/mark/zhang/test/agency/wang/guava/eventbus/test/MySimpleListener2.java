package com.test.mark.zhang.test.agency.wang.guava.eventbus.test;


import com.test.mark.zhang.test.agency.wang.guava.eventbus.internal.MySubscribe;

import java.util.concurrent.TimeUnit;

/***************************************
 * @author:Alex Wang
 * @Date:2017/10/21
 * 532500648
 ***************************************/
public class MySimpleListener2 {

    @MySubscribe
    public void test1(String x) {
        System.out.println("MySimpleListener2===test1==" + x);
    }

    @MySubscribe(topic = "alex-topic")
    public void test2(Integer x) {
        try {
            //禁忌:使用中间件和这种解耦的工具,注意不要阻塞
            //active是基于push消息给消费者的,假如消费者收到消息后,立即在当前工作线程去工作,势必导致activeMQ的吞吐量降低----阻塞
            //kafka是基于pull的形式,消费者主动拉去消息,同样,假如直接收到消息后,立即在当前线程工作,也会导致吞吐量降低
            //应该是收到消息后,交给另外的工作线程去处理,而不是阻塞消费线程,导致吞吐量下降
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MySimpleListener2===test2==" + x);
    }

    @MySubscribe(topic = "test-topic")
    public void test3(Integer x) {
        throw new RuntimeException();
    }
}
