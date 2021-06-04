package com.test.mark.zhang.test.delayQueue.project;

import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author mark
 * 生产消费模式的三种形式
 * 使用Object的wait/notify的消息通知机制；
 * 使用Lock的Condition的await/signal的消息通知机制；
 * 使用BlockingQueue实现。
 */
@Component
public class EventIdBasket {
    //public static DelayQueue<EventIdDTO> eventQ = new DelayQueue<>();
    //阻塞队列,延迟队列,优先队列
    public static BlockingDeque<EventIdDTO> eventQ = new LinkedBlockingDeque<>();

    public static void produce(EventIdDTO eventIdDTO) {
        eventQ.add(eventIdDTO);
    }

    public static EventIdDTO consume() throws InterruptedException {
        return eventQ.take();
    }

}
