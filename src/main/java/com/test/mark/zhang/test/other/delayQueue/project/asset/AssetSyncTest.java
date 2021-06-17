package com.test.mark.zhang.test.other.delayQueue.project.asset;

import com.alibaba.fastjson.JSONObject;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Classname AssetSyncTest
 * @Description 资产同步
 * @Date 2021/6/11 2:40 下午
 * @Created by mark
 */
public class AssetSyncTest {

    private static final LinkedBlockingDeque<Runnable> QUEUE  = new LinkedBlockingDeque<>();
    private static final List<Thread> LIST = new ArrayList<>();
    private static final JSONObject JSON_OBJECT = new JSONObject();
    private static  ExecutorService EXECUTOR;


    @PostConstruct
    public void init() {
        //init executor
        EXECUTOR = null;
    }

    public static void main(String[] args) {

    }

    // 定时任务,队列为零,进行资产同步
    private static JSONObject assetSync() {
        if ("1".equals("1")) {
            //资产同步打开
            if (QUEUE.size() > 0) {
                Lock lock = new ReentrantLock();
                lock.lock();
                //lock()必须紧跟try   why
                try {
                    LIST.add(Thread.currentThread());
                    LockSupport.park(Thread.currentThread());
                } catch (Exception e) {
                    e.getStackTrace();
                } finally {
                    lock.unlock();
                }
                LIST.remove(Thread.currentThread());
                return JSON_OBJECT;
            } else {
                JSONObject jsonObject = null;
                try {
                    Future<JSONObject> future = EXECUTOR.submit(new Callable<JSONObject>() {
                        @Override
                        public JSONObject call() throws Exception {
                            return doAssetSync();
                        }
                    });
                    jsonObject = future.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Lock lock = new ReentrantLock();
                lock.lock();
                // notifyUnPark 遍历集合,执行unpark
            }
        } else {
            System.out.println("资产同步未打开");
        }

        return null;
    }

    private static JSONObject doAssetSync() {

        return null;
    }
}
