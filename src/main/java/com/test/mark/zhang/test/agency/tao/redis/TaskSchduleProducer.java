package com.test.mark.zhang.test.agency.tao.redis;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * 任务调度模拟案例： 任务生产者
 */
public class TaskSchduleProducer implements Runnable {
    Jedis jedis = null;
    Random random = null;

    public TaskSchduleProducer() {
        jedis = new Jedis("doitedu01", 6379);
        random = new Random();
    }


    @Override
    public void run() {

        // 生成任务，并放入调度队列
        for (int i = 0; i < 1000; i++) {
            jedis.lpush("task:queue", "task-" + i);
            try {
                long l = random.nextInt(1000);
                System.out.println("休眠时间: " + l);
                Thread.sleep(l);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
