package com.test.mark.zhang.test.agency.wang.thread.classloader.concurrent.juc.utils.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/6
 * QQ交流群:601980517，463962286
 ***************************************/
public class ExchangerExample3 {
    public static void main(String[] args) {

        final Exchanger<Integer> exchanger = new Exchanger<Integer>();

        new Thread() {
            @Override
            public void run() {
                AtomicReference<Integer> value = new AtomicReference<Integer>(1);

                try {
                    while (true) {
                        value.set(exchanger.exchange(value.get()));
                        System.out.println("Thread A has Value:" + value.get());
                        TimeUnit.SECONDS.sleep(3);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                AtomicReference<Integer> value = new AtomicReference<Integer>(2);

                try {
                    while (true) {
                        value.set(exchanger.exchange(value.get()));
                        System.out.println("Thread B has Value:" + value.get());
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
