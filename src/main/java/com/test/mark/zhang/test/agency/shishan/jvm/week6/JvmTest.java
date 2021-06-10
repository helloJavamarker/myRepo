package com.test.mark.zhang.test.agency.shishan.jvm.week6;

/**
 * @Classname JvmTest
 * @Description TODO
 * @Date 2021/6/8 7:26 下午
 * @Created by mark
 */
public class JvmTest {
    public static void main(String[] args) {
        //首先设置jvm启动参数  vm options,,新版默认隐藏    每个人机器不一样,实际log日志可能不同
        //-XX:NewSize=5242880 -XX:MaxNewSize=5242880 -XX:InitialHeapSize=10485760
        //-XX:MaxHeapSize=10485760 -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=10485760
        //-XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
        //-Xloggc:gc.log

        // 参数解析: 一共10M内存,5M新生代(4M eden,0.5 + 0.5 s区),5M老年代  大对象阈值是10M
        // 在target目录下会生成gc.log

        //实际创建的对象不止这个数组,还有其他对象
        byte[] byte1 = new byte[1024 * 1024];
        //在JVM的Eden区内放入一个1MB的对象，同时在main线程的虚拟机栈中会压入一个main()
        //方法的栈帧，在main()方法的栈帧内部，会有一个“array1”变量，这个变量是指向堆内存Eden区的那个1MB的数组，
        byte1 = new byte[1024 * 1024];
        // 第一个数组变成了垃圾
        byte1 = new byte[1024 * 1024];
        //第二个成了垃圾
        byte1 = null;
        //三个都是垃圾
        byte1 = new byte[2 * 1024 * 1024];
        //这里会有垃圾回收--新生代

        //这里全程没有调用system.gc()

        // log分析
        //[ParNew: 3560K->512K(4608K), 0.0045465 secs] 3560K->1026K(9728K), 0.0047212 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
        // (4608K)表示新生代共有共有4608k,4.5M===新生代必须保证两个s区至少有一个是空的
        // 3560K->512K 表示对年轻代执行一个gc后,对象由原来的3560变成了512k====剩512k对象存活....耗费了4.5毫秒
        // (9728K) 整个堆内存可用空间是9.5M====4.5 + 5   老年代没用到

        //eden space 4096K,  51% used [0x00000007bf600000, 0x00000007bf8113a8, 0x00000007bfa00000)   存活了一个2M的数组

        //from space 512K,  58% used [0x00000007bfa00000, 0x00000007bfa4b690, 0x00000007bfa80000)    被之前gc后存活下来的512KB的未知对象给占据了

        // concurrent mark-sweep generation total 5120K, used 1026K [0x00000007bfb00000, 0x00000007c0000000, 0x00000007c0000000)
        // Metaspace       used 3055K, capacity 4496K, committed 4864K, reserved 1056768K
        //  class space    used 332K, capacity 388K, committed 512K, reserved 1048576K
        //Concurrent Mark-Sweep垃圾回收器，也就是CMS垃圾回收器，管理的老年代内存空间一共是5MB，此时使用了1026KB的空间，这个是啥你也先不用管了，可以先忽略不计，以后我
        //们有内存分析工具了，你都能看到。
        //Metaspace元数据空间和Class空间，存放一些类信息、常量池之类的东西，此时他们的总容量，使用内存，等等。

    }
}
