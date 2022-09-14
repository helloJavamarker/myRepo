package com.test.mark.zhang.test.agency.wang.thread.classloader.concurrent.juc.executors;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/25
 * QQ交流群:601980517，463962286
 ***************************************/
public class ExecutorServiceExample1 {
    /**
     * The demo for class {@link ExecutorService}
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
//        isShutDown();
//        isTerminated();
//        executeRunnableError();
        executeRunnableTask();
    }

    @Test
    public void testThreadPool() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1));
//        threadPoolExecutor.submit(() -> {
//            System.out.println("........");
//            String a = null;
//            a.equals("z");
////            "z".equals(a);   submit 出异常会偷偷处理,下面代码不会再执行
//            System.out.println("...");
//        });
        threadPoolExecutor.execute(() -> {
            System.out.println("........");
            String a = null;
            a.equals("z");
//            "z".equals(a);   submit 出异常会偷偷处理,下面代码不会再执行
            System.out.println("...");
        });
        //1、submit在执行过程中与execute不一样，不会抛出异常而是把异常保存在成员变量中，在FutureTask.get阻塞获取的时候再把异常抛出来。
        //2、Spring的@Schedule注解的内部实现就是使用submit，因此，如果你构建的任务内部有未检查异常，你是永远也拿不到这个异常的。
        //3、execute直接抛出异常之后线程就死掉了，submit保存异常线程没有死掉，因此execute的线程池可能会出现没有意义的情况，因为线程没有得到重用。而submit不会出现这种情况。
    }

    /**
     * Question:
     * <p>
     * When invoked the shutdown method, can execute the new runnable?
     * Answer:
     * No!!! the Executor Service will rejected after shutdown.
     * {@link ExecutorService#isShutdown()}
     * {@link ExecutorService#shutdown()}
     */
    private static void isShutDown() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(executorService.isShutdown());
//        executorService.shutdown();
        System.out.println(executorService.isShutdown());
        executorService.execute(() -> System.out.println("i will be executed after shutdown???"));
    }

    /**
     * {@link ExecutorService#isTerminated()}
     * {@link ThreadPoolExecutor#isTerminating()}
     */
    private static void isTerminated() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
        System.out.println(executorService.isShutdown());
        System.out.println(executorService.isTerminated());
        System.out.println(((ThreadPoolExecutor) executorService).isTerminating());
    }


    private static void executeRunnableError() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10, new MyThreadFactory());
        IntStream.range(0, 10).boxed().forEach(i -> executorService.execute(() -> System.out.println(1 / 0)));
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("========================");
    }

    /**
     * <pre>
     *                                  |---->
     *                                  |---->
     * send request---->store db--->10->|---->
     *                                  |---->
     *                                  |---->
     * </pre>
     */
    private static void executeRunnableTask() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10, new MyThreadFactory());
        IntStream.range(0, 10).boxed().forEach(i -> executorService.execute(
                new MyTask(i) {
                    @Override
                    protected void error(Throwable cause) {
                        System.out.println("The no:" + i + " failed, update status to ERROR");
                    }

                    @Override
                    protected void done() {
                        System.out.println("The no:" + i + " successfully, update status to DONE");
                    }

                    @Override
                    protected void doExecute() {
                        if (i % 3 == 0) {
                            int tmp = i / 0;
                        }
                    }

                    @Override
                    protected void doInit() {
                        //do nothing.
                    }
                }
        ));

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("========================");
    }

    private abstract static class MyTask implements Runnable {

        protected final int no;

        private MyTask(int no) {
            this.no = no;
        }

        @Override
        public void run() {
            try {
                this.doInit();
                this.doExecute();
                this.done();
            } catch (Throwable cause) {
                this.error(cause);
            }
        }

        protected abstract void error(Throwable cause);

        protected abstract void done();

        protected abstract void doExecute();

        protected abstract void doInit();
    }

    private static class MyThreadFactory implements ThreadFactory {

        private final static AtomicInteger SEQ = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("My-Thread-" + SEQ.getAndIncrement());
            thread.setUncaughtExceptionHandler((t, cause) -> {
                System.out.println("The thread " + t.getName() + " execute failed.");
                cause.printStackTrace();
                System.out.println("=======================================");
            });
            return thread;
        }
    }
}
