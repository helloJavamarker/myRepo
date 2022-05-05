package com.test.mark.zhang.test.agency.tao.redis;

/**
 *
 */
public class TaskScheduleOnRedisList {

    public static void main(String[] args) {

        System.out.println("启动任务生产者");
        new Thread(new TaskSchduleProducer()).start();

        System.out.println("启动任务消费者者");
        new Thread(new TaskScheduleConsumer()).start();

    }
}
