package com.test.mark.zhang.test.agency.wang.thread.classloader.concurrent.juc.utils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/7
 * QQ交流群:601980517，463962286
 ***************************************/
public class SemaphoreExample2 {

    /**
     * connection pool
     * When get the not available connection/policy
     * 1.Get 1000MS then throw exception
     * 2.blocking
     * 3.discard
     * 4.Get then throw exception
     * 5.get->register the callback,-> call you.
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        final Semaphore semaphore = new Semaphore(2);

        for (int i = 0; i < 3; i++)
            new Thread() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " in");
                    try {
                        semaphore.acquire(1);
                        System.out.println(Thread.currentThread().getName() + " Get the semaphore.");
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release(1);
                    }
                    System.out.println(Thread.currentThread().getName() + " out");
                }
            }.start();


        while (true) {

            System.out.println("AP->" + semaphore.availablePermits());
            System.out.println("QL->" + semaphore.getQueueLength());
            System.out.println("===========================");

            TimeUnit.SECONDS.sleep(1);
        }
    }
}
