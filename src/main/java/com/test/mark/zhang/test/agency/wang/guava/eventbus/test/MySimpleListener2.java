package com.test.mark.zhang.test.agency.wang.guava.eventbus.test;


import com.test.mark.zhang.test.agency.wang.guava.eventbus.internal.MySubscribe;

import java.util.concurrent.TimeUnit;

/***************************************
 * @author:Alex Wang
 * @Date:2017/10/21
 * 532500648
 ***************************************/
public class MySimpleListener2
{

    @MySubscribe
    public void test1(String x)
    {
        System.out.println("MySimpleListener2===test1==" + x);
    }

    @MySubscribe(topic = "alex-topic")
    public void test2(Integer x)
    {
        try
        {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("MySimpleListener2===test2==" + x);
    }

    @MySubscribe(topic = "test-topic")
    public void test3(Integer x)
    {
        throw new RuntimeException();
    }
}
