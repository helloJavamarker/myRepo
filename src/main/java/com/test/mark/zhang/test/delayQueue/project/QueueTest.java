package com.test.mark.zhang.test.delayQueue.project;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.concurrent.Executors;

/**
 * @author mark
 */
public class QueueTest {

    public static void main(String[] args) {

        // todo 使用生产消费模式,可以避免while(true)   wait/notify+volatile
        Thread thread = new Thread(() -> {
            while (true) {
                // while(true) 占用CPU内存!!!!!  不会放弃cpu执行权

                /**
                 * Stack Overflow
                 *
                 * The CPU cannot do anything else while it's executing that loop (which never ends).
                 * Even if you're using a pre-emptive multi-tasking system (so that infinite loop will only clog forever its own process
                 * or thread), the loop will "eat" its time slice each time the OS's pre-emptive scheduler hands it the CPU for the next slice
                 * -- doing nothing, but eating up one slice's worth of CPU time each and every time,
                 * so that much CPU is lost to all other threads which could otherwise be doing useful work.
                 */
                try {
                    EventIdDTO consume = EventIdBasket.consume();
                    if (StringUtils.isNoneBlank(consume.getLogEventId())) {
                        System.out.println("search from es map.....");
                        System.out.println("send msg!"  + consume);
                    }
                    //Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        Executors.newCachedThreadPool().execute(() -> {
            System.out.println("search data from es.....");
            System.out.println("转换");
            for (int i = 0; i < 100; i++) {
                EventIdDTO dto = new EventIdDTO();
                dto.setCreateTime(new Date());
                dto.setAlarmEventId("AlarmEventId:" + i);
                dto.setLogEventId("LogEventId:" + i);
                EventIdBasket.produce(dto);
            }
        });

        Executors.newCachedThreadPool().execute(() -> {
            System.out.println("search data from es.....");
            System.out.println("转换");
            for (int i = 1000; i < 1100; i++) {
                EventIdDTO dto = new EventIdDTO();
                dto.setCreateTime(new Date());
                dto.setAlarmEventId("AlarmEventId:" + i);
                dto.setLogEventId("LogEventId:" + i);
                EventIdBasket.produce(dto);
            }
        });

        //ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool();
        // 定时执行 todo
    }
}
