package com.test.mark.zhang.test.other.delayQueue.project.asset;

import java.util.concurrent.locks.LockSupport;

/**
 * @Classname LockSupportDemo
 * @Description  文章见: https://www.jianshu.com/p/f1f2cd289205
 * @Date 2021/6/11 3:54 下午
 * @Created by mark
 */
public class LockSupportDemo {
    //public static void park(Object blocker); // 暂停当前线程
    //public static void parkNanos(Object blocker, long nanos); // 暂停当前线程，不过有超时时间的限制
    //public static void parkUntil(Object blocker, long deadline); // 暂停当前线程，直到某个时间
    //public static void park(); // 无期限暂停当前线程
    //public static void parkNanos(long nanos); // 暂停当前线程，不过有超时时间的限制
    //public static void parkUntil(long deadline); // 暂停当前线程，直到某个时间
    //public static void unpark(Thread thread); // 恢复当前线程
    //public static Object getBlocker(Thread t);

    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super(name);
        }
        @Override public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                LockSupport.park();
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("被中断了");
                }
                System.out.println("继续执行");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(1000L);
        t2.start();
        Thread.sleep(3000L);
        t1.interrupt();
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}
