package com.test.mark.zhang.test.agency.shishan.jvm.week6;

/**
 * @Classname JvmTest2
 * @Description TODO
 * @Date 2021/6/8 8:15 下午
 * @Created by mark
 */
public class JvmTest2 {
    // jvm设置-XX:NewSize=10485760 -XX:MaxNewSize=10485760 -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520
    //-XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=10485760 -XX:+UseParNewGC
    //-XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log
    //新生代10M,8:1:1   共20M,15次计入老年代  大对象10M   新的gc日志会覆盖之前的

    public static void main(String[] args) {
        byte[] bytes1 = new byte[2 * 1024 * 1024];
        bytes1 = new byte[2 * 1024 * 1024];
        bytes1 = new byte[2 * 1024 * 1024];
        bytes1 = null;
        byte[] bytes2 = new byte[128 * 1024];
        byte[] bytes3 = new byte[2 * 1024 * 1024];

        //[ParNew: 8013K->1024K(9216K), 0.0045887 secs] 8013K->3097K(19456K), 0.0047825 secs]
        //yuong GC后,由原来占用8013,到现在占用1024

        //eden space 8192K,  55% used
        //from space 1024K, 100% used
        //to   space 1024K,   0% used

    }
}
