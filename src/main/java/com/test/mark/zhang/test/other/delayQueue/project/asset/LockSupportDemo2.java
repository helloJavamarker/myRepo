package com.test.mark.zhang.test.other.delayQueue.project.asset;

import com.alibaba.fastjson.JSONObject;
import com.test.mark.zhang.entity.Person;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.ManagedBean;
import java.util.Optional;
import java.util.concurrent.locks.LockSupport;

/**
 * @Classname LockSupportDemo2
 * @Description TODO
 * @Date 2021/6/11 4:01 下午
 * @Created by mark
 *
 * park和unpark可以实现类似wait和notify的功能，但是并不和wait和notify交叉，也就是说unpark不会对wait起作用，notify也不会对park起作用。
 * park和unpark的使用不会出现死锁的情况
 * blocker的作用是在dump线程的时候看到阻塞对象的信息
 */
public class LockSupportDemo2 {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");

    public static class ChangeObjectThread extends Thread {

        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LockSupport.park();
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("被中断了");
                }
                System.out.println("继续执行");
            }
        }
    }

    public static void main(String[] args) {
        t1.start();
        LockSupport.unpark(t1);
        System.out.println("unpark invoked");
    }
}
