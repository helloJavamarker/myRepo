package com.test.mark.zhang.test.agency.shishan.jvm.week8;

/**
 * @Classname JvmTest
 * @Description TODO
 * @Date 2021/6/9 2:43 下午
 * @Created by mark
 */
public class GcTest {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(30000);
        //留出时间,先用jps查看java端口,再用jstat -gc PID 1000 1000 查看,每秒执行一次,执行一千次
        while (true){
            loadData();
        }

        //使用jstat观察内存使用情况
        //新生代对象增长的速率
        //Young GC的触发频率
        //Young GC的耗时
        //每次Young GC后有多少对象是存活下来的
        //每次Young GC过后有多少对象进入了老年代
        //老年代对象增长的速率
        //Full GC的触发频率
        //Full GC的耗时
    }

    // 模拟每秒加载50M数据到Eden区  堆内存共200M,新生代100M
    private static void loadData() throws InterruptedException {
        byte[] data = null;
        for (int i = 0; i < 50; i++) {
            data = new byte[100 * 1024];
        }
        data = null;
        Thread.sleep(1000);
    }
}
