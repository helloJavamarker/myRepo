package com.test.mark.zhang.test.agency.wang.thread.classloader.concurrent.juc.collections.blocking;

import java.util.concurrent.LinkedBlockingDeque;

/***************************************
 * @author:Alex Wang
 * @Date:2017/9/15
 * QQ交流群:601980517，463962286
 ***************************************/
public class LinkedBlockingDequeExample {

    public static <T> LinkedBlockingDeque<T> create() {
        //ArrayDeque 是 Deque 接口的一种具体实现，是依赖于可变数组来实现的。ArrayDeque 没有容量限制，可根据需求自动进行扩容。
        // ArrayDeque 可以作为栈来使用，效率要高于 Stack。ArrayDeque 也可以作为队列来使用，效率相较于基于双向链表的 LinkedList 也要更好一些。
        // 注意，ArrayDeque 不支持为 null 的元素。 ---- 总之,不建议使用stack,因为性能低
        return new LinkedBlockingDeque<>();
    }
}
