package com.test.mark.zhang.test.agency.wang.thread.classloader.concurrent.juc.executors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/26
 * QQ交流群:601980517，463962286
 ***************************************/
public class FutureExample2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        testIsDone();
//        testCancel();
        testCancel2();
    }

    /**
     * Completion may be due to normal termination, an exception, or
     * cancellation
     */
    private static void testIsDone() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Integer> future = executorService.submit(() -> {
            throw new RuntimeException();
        });
        try {
            Integer result = future.get();
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(" is done " + future.isDone());
        }
    }

    /**
     * try to cancel maybe failed
     * <ul>
     * <li>task is completed already.</li>
     * <li>has already been cancelled.</li>
     * </ul>
     */
    private static void testCancel() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicBoolean running = new AtomicBoolean(true);
        Future<Integer> future = executorService.submit(() -> {
            /*try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            while (running.get()) {
            }
            return 10;
        });

        TimeUnit.MILLISECONDS.sleep(10);
        System.out.println(future.cancel(true));
        System.out.println(future.isDone());
        System.out.println(future.isCancelled());

    }

    /**
     * try to cancel maybe failed
     * <ul>
     * <li>task is completed already.</li>
     * <li>has already been cancelled.</li>
     * </ul>
     */
    private static void testCancel2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicBoolean running = new AtomicBoolean(true);
        Future<Integer> future = executorService.submit(() -> {
            /*try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("=======================");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
/*            while (running.get()) {
            }*/
            while (!Thread.interrupted()) {
                //sdfsdfs

            }
            System.out.println("1111111");
            return 10;
        });

        TimeUnit.MILLISECONDS.sleep(10);
        System.out.println(future.cancel(true));
        System.out.println(future.isDone());
        System.out.println(future.isCancelled());
        TimeUnit.MILLISECONDS.sleep(20);
        System.out.println(future.get());

    }
}
