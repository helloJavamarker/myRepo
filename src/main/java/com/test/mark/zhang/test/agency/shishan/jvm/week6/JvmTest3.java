package com.test.mark.zhang.test.agency.shishan.jvm.week6;

/**
 * @Classname JvmTest3
 * @Description TODO
 * @Date 2021/6/9 9:12 上午
 * @Created by mark
 */
public class JvmTest3 {
    //对jvmTest2代码改进,导致第二次gc
    public static void main(String[] args) {
        byte[] bytes1 = new byte[2 * 1024 * 1024];
        bytes1 = new byte[2 * 1024 * 1024];
        bytes1 = new byte[2 * 1024 * 1024];
        bytes1 = null;
        byte[] bytes2 = new byte[128 * 1024];
        //这里128k对象一直会存活,先被移到from区
        byte[] bytes3 = new byte[2 * 1024 * 1024];
        //byte3也会存活
        // 触发第一次gc

        bytes3 = new byte[2 * 1024 * 1024];
        bytes3 = new byte[2 * 1024 * 1024];
        bytes3 = new byte[2 * 1024 * 1024];
        bytes3 = null;

        byte[] bytes4 = new byte[2 * 1024 * 1024];
        //触发第二次gc
        //byte4也会存活

        //ParNew: 7841K->1023K(9216K), 0.0022884 secs]
        //ParNew: 7536K->537K(9216K), 0.0053878 secs]
        // 第二次触发gc,存活对象只有537K

        //年龄1+年龄2+年龄n的对象总大小超过了Survivor区域的50%，年龄n以上的对象进入老年代。   动态年龄判断是超过一个s区的50%
        //concurrent mark-sweep generation total 10240K, used 4975K
    }
}
