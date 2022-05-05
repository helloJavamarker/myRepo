package com.test.mark.zhang.test.agency.tao.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CallableDemo {
    public static void main(String[] args) throws Exception {


        /*new Thread(new Runnable() {
            @Override
            public void run() {

                int i = 0;
                while(i<100){
                    System.out.println(i);
                    i++;
                }
            }
        }).start();
*/

        System.out.println("准备开启一个线程去工作......");


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                int i = 0;
                while (i < 20) {
                    System.out.println(i);
                    i++;
                    Thread.sleep(1000);
                }
                return "ok";
            }
        });

        boolean fetched = false;

        System.out.println("我要去获取线程的执行返回结果，最多等500ms");
        try {
            String s = future.get(500L, TimeUnit.MILLISECONDS);
            System.out.println(s);
        } catch (Exception e) {
            System.out.println("没有拿到");
        }

        if (!fetched) {
            System.out.println("我要去获取线程的执行返回结果，一直等到拿到为止");
            try {
                String s = future.get();
                System.out.println(s);
                executorService.shutdown();
            } catch (Exception e) {
                System.out.println("没有拿到");
            }
        }

    }

}
