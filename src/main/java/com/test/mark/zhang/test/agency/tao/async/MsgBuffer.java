package com.test.mark.zhang.test.agency.tao.async;

import java.util.concurrent.ArrayBlockingQueue;

/*** 中游，缓冲池 */
class MsgBuffer {
    ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(100);

    public void put(String msg) throws Exception {
        queue.put(msg);
    }

    public String take() throws Exception {
        return queue.take();
    }
}