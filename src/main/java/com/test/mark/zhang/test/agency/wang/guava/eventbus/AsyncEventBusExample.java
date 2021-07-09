package com.test.mark.zhang.test.agency.wang.guava.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.test.mark.zhang.test.agency.wang.guava.eventbus.listeners.SimpleListener;

import java.util.concurrent.Executor;

/***************************************
 * @author:Alex Wang
 * @Date:2017/10/21
 * 532500648
 ***************************************/
public class AsyncEventBusExample {
    public static void main(String[] args) {
        AsyncEventBus eventBus = new AsyncEventBus(new SeqExecutor());
        eventBus.register(new SimpleListener());
        eventBus.post("hello");

    }

    static class SeqExecutor implements Executor {

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }
}
