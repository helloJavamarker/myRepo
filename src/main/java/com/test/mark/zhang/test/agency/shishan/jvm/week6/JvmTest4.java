package com.test.mark.zhang.test.agency.shishan.jvm.week6;

/**
 * @Classname JvmTest4
 * @Description TODO
 * @Date 2021/6/9 9:39 上午
 * @Created by mark
 */
public class JvmTest4 {
    public static void main(String[] args) {
        //模拟老年代gc,,,翻车,实际并没有触发fullGC
        //-XX:NewSize=10485760 -XX:MaxNewSize=10485760 -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520
        // -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=3145728 -XX:+UseParNewGC
        // -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log

        //-XX:PretenureSizeThreshold=3145728 大对象阈值设为3M  bytes1直接进入老年代
        byte[] bytes1 = new byte[4 * 1024 * 1024];
        bytes1 = null;

        byte[] bytes2 = new byte[2 * 1024 * 1024];
        byte[] bytes3 = new byte[2 * 1024 * 1024];
        byte[] bytes4 = new byte[2 * 1024 * 1024];
        byte[] bytes5 = new byte[128 * 1024];

        byte[] bytes6 = new byte[2 * 1024 * 1024];
        //触发一次yuongGC:[ParNew: 7849K->1023K(9216K), 0.0043163 secs]
        // 老师的是: 7260K->7970K(9216K), 0.0048975 secs,,Eden区原来是有7000多KB的对象，但是回收之后发现一个都回收不掉,都被引用着

        // concurrent mark-sweep generation total 10240K, used 8234K


        //老师的日志
        // [ParNew (promotion failed): 7260K->7970K(9216K), 0.0048975 secs]0.314: [CMS:
        //8194K->6836K(10240K), 0.0049920 secs] 11356K->6836K(19456K), [Metaspace: 2776K->2776K(1056768K)], 0.0106074 secs]
        //[Times: user=0.00 sys=0.00, real=0.01 secs]
        //实际应该是bytes1先被移到老年代
        //创建bytes6的时候开始yuonggc,但是所有对象都被引用,再移到老年代,触发老年gc,将bytes1回收
    }
}
