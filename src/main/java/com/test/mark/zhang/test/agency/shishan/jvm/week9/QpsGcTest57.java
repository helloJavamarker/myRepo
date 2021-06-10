package com.test.mark.zhang.test.agency.shishan.jvm.week9;

/**
 * @Classname QpsGcTest
 * @Description TODO
 * @Date 2021/6/9 4:17 下午
 * @Created by mark
 */
public class QpsGcTest57 {

    //-XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=5  在5次fullGC后,执行一次压缩,整理碎片
    //对于高并发场景.常用解决办法有:1,增加机器数量,减少单个机器的压力,2,给s区增大空间,避免大对象进入老年代
    //CMS垃圾回收器默认是采用标记-清理算法，所以是会造成大量的内存碎片的。当高qps情况时,产生大量碎片,导致利用率不够,进入老年代

    //“-XX:CMSInitiatingOccupancyFraction=68”  老年代占用达到68%后,触发一次fullGC
    public static void main(String[] args) {

    }
}
