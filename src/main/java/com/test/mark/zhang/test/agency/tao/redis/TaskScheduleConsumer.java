package com.test.mark.zhang.test.agency.tao.redis;


import redis.clients.jedis.Jedis;

import java.util.Random;


/**
 * 任务调度模拟案例：任务消费者
 */
public class TaskScheduleConsumer implements Runnable {

    Jedis jedis = null;
    Random random = null;

    public TaskScheduleConsumer() {
        jedis = new Jedis("doitedu01", 6379);
        random = new Random();
    }


    @Override
    public void run() {

        while (true) {
            // 从队列中获取一个任务，并放入一个临时队列(inflight-queue)
            String task = jedis.rpoplpush("task:queue", "task:inflight");

            if (task == null) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            // 处理任务,模拟可能发生错误，也可能成功
            if (random.nextInt(20) % 5 != 0) {
                // 任务处理成功的情况，从inflight队列中清除
                jedis.rpop("task:inflight");
                System.out.println("任务-" + task + " 处理成功");
            } else {
                // 如果失败，则从inflight中弹出并放入 任务队列
                jedis.rpoplpush("task:inflight", "task:queue");
                System.out.println("任务-" + task + " 处理失败,并放回任务队列等待下一次处理");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
