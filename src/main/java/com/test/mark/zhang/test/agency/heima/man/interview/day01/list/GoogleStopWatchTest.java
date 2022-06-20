package com.test.mark.zhang.test.agency.heima.man.interview.day01.list;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author by mark
 * @Classname GoogleStopWatchTest
 * @Description TODO
 * @Date 2022/5/17 10:29 上午
 */
@Slf4j
public class GoogleStopWatchTest {
    /**
     * google stopWatch 和system.nanoTime()对比,形式更多  guava
     * spring stopWatch 更适合展示和统计
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        String orderNo = "12345678";

        log.info("订单 [{}] 开始处理", orderNo);
        Stopwatch stopwatch = Stopwatch.createStarted();

        TimeUnit.SECONDS.sleep(1);  // 1秒处理时间

        log.info("订单 [{}] 处理完成，耗时 [{}]", orderNo, stopwatch.stop());

    }

    @Test
    public void test2() throws Exception {
        // 创建stopwatch并开始计时
        Stopwatch stopwatch = Stopwatch.createStarted();
        Thread.sleep(1980);

        // 以秒打印从计时开始至现在的所用时间，向下取整
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS)); // 1

        // 停止计时
        stopwatch.stop();
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS)); // 1

        // 再次计时
        stopwatch.start();
        Thread.sleep(100);
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS)); // 2

        // 重置并开始
        stopwatch.reset().start();
        Thread.sleep(1030);

        // 检查是否运行
        System.out.println(stopwatch.isRunning()); // true
        long millis = stopwatch.elapsed(TimeUnit.MILLISECONDS); // 1034
        System.out.println(millis);

        // 打印
        System.out.println(stopwatch); // 1.034 s
    }

}
