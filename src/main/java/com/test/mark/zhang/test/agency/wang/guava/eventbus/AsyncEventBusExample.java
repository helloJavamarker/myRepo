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
        //Executor executor 这里异步执行是依赖于传入的executor,但是假如传入的executor本身就是串行的,那并行就不成立
        //以后写代码也要注意,一个功能假如依赖传入的参数,那就要考虑传入的参数是不是一定可以实现某个功能
        AsyncEventBus eventBus = new AsyncEventBus(new SeqExecutor());
        eventBus.register(new SimpleListener());
        eventBus.post("hello");

    }

    static class SeqExecutor implements Executor {

        //这里重写run,策略模式----模板方法
        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }
}
