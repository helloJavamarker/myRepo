package com.test.mark.zhang.test.agency.wang.guava.concurrent;

import com.google.common.util.concurrent.FutureCallback;
import reactor.util.annotation.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/***************************************
 * @author:Alex Wang
 * @Date:2017/11/12
 * QQ: 532500648
 * QQ群:463962286
 ***************************************/
public class ListenableFutureExample
{
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        ExecutorService service = Executors.newFixedThreadPool(2);

        /*Future<Integer> future = service.submit(() ->
        {
            try
            {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            return 10;
        });

        Object result = future.get();
        System.out.println(result);*/

 /*       ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(service);
        ListenableFuture<Integer> future = listeningExecutorService.submit(() ->
        {
            try
            {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            return 100;
        });

//        future.addListener(() -> System.out.println("I am finished"), service);

        Futures.addCallback(future, new MyCallBack(), service);
        System.out.println("=============");*/

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() ->
        {
            try
            {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            return 100;
        }, service).whenComplete((v, t) -> System.out.println("I am finished and the result is " + v));
    }

    static class MyCallBack implements FutureCallback<Integer>
    {

        @Override
        public void onSuccess(@Nullable Integer result)
        {
            System.out.println("I am finished and the result is " + result);
        }

        @Override
        public void onFailure(Throwable t)
        {
            t.printStackTrace();
        }
    }
}
