package com.test.mark.zhang.test.agency.shishan.jvm.week8;

/**
 * @Classname FullGcTest
 * @Description TODO
 * @Date 2021/6/9 3:43 下午
 * @Created by mark
 */
public class FullGcTest {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(30000);
        while (true) {
            loadData();
        }
    }

    // 要避免每次产生的垃圾对象太大,导致s区放不下,直接进入老年代
    //优化前配置:-XX:NewSize=104857600 -XX:MaxNewSize=104857600 -XX:InitialHeapSize=209715200 -XX:MaxHeapSize=209715200
    // -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=20971520 -XX:+UseParNewGC
    // -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log


    //调整后建议参数,避免每次yuongGC后存活对象太多,导致频繁fullGC
    //-XX:NewSize=209715200 -XX:MaxNewSize=209715200 -XX:InitialHeapSize=314572800 -XX:MaxHeapSize=314572800
    //-XX:SurvivorRatio=2 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=20971520 -XX:+UseParNewGC
    //-XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log
    //把堆大小调大为了300MB，年轻代给了200MB，同时“-XX:SurvivorRatio=2”表明，Eden:Survivor:Survivor的比例为2:1:1，
    //所以Eden区是100MB，每个Survivor区是50MB，老年代也是100MB。
    private static void loadData() throws InterruptedException {
        byte[] data = null;
        for (int i = 0; i < 4; i++) {
            data = new byte[10 * 1024 * 1024];
        }
        data = null;
        byte[] data1 = new byte[10 * 1024 *1024];
        byte[] data2 = new byte[10 * 1024 *1024];
        //data1 和data2必须存活

        byte[] data3 = new byte[10 * 1024 *1024];
        data3 = new byte[10 * 1024 * 1024];
        //data3指向另一个对象后,会产生垃圾10M
        Thread.sleep(1000);
    }
}
