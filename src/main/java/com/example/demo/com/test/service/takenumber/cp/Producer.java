package com.example.demo.com.test.service.takenumber.cp;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 取号不适合用生产消费模式
 * 因为票是固定的
 */
public class Producer implements Runnable{
    private volatile boolean isFull = true;
    private Queue workQueue = new PriorityQueue();
    //private BlockingQueue<PCData> queue;// 内存缓冲区
    private static AtomicInteger count = new AtomicInteger(0);
    private static final int NORMAL_TOTAL = 10;
    private static final int SMALL_TOTAL = 8;

    @Override
    public void run() {

    }
}
