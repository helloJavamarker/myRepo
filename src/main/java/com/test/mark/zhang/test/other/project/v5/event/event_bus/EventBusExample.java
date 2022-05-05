package com.test.mark.zhang.test.other.project.v5.event.event_bus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.concurrent.Executors;

/**
 * @author by mark
 * @Classname EventBusExample
 * @Description TODO
 * @Date 2022/4/28 4:31 下午
 */
public class EventBusExample {
    //EventBus 是 Guava 的事件处理机制，是观察者模式（生产/消费模型）的一种实现。
    //
    //观察者模式在我们日常开发中使用非常广泛，例如在订单系统中，订单状态或者物流信息的变更会向用户发送APP推送、短信、通知卖家、买家等等；
    // 审批系统中，审批单的流程流转会通知发起审批用户、审批的领导等等。
    //
    //Observer模式也是 JDK 中自带就支持的，其在 1.0 版本就已经存在 Observer，不过随着 Java 版本的飞速升级，其使用方式一直没有变化，
    // 许多程序库提供了更加简单的实现，例如 Guava EventBus、RxJava、EventBus 等
    //
    //一、为什么要用 Observer模式以及 EventBus 优点 ？
    //EventBus 优点
    //
    //相比 Observer 编程简单方便
    //通过自定义参数可实现同步、异步操作以及异常处理
    //单进程使用，无网络影响
    //缺点
    //
    //只能单进程使用
    //项目异常重启或者退出不保证消息持久化
    //如果需要分布式使用还是需要使用 MQ

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();// 同步


//        EventBus asyncEventBus = new AsyncEventBus(Executors.newCachedThreadPool());


        eventBus.register(new EventListener());

        eventBus.post(1);
        eventBus.post(2);
        eventBus.post("str");

    }
}
