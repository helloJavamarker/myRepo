package com.test.mark.zhang.test.agency.tao.async;

public class CallDemo {
    public static void main(String[] args) throws Exception {
        MsgBuffer msgBuffer = new MsgBuffer();
        /* 启动生产者，生成数据，只管自己生成，不用等待后续的处理 */
        new Thread(() -> {
            GenMessage2 genner = new GenMessage2(msgBuffer);
            try {
                // 生产数据
                genner.genMsg();
            } catch (Exception e) {
            }
        }).start();
        // 启动消费者，处理数据，只管自己处理，不用关心数据的生产
        new Thread(() -> {
            MsgProcessor2 processor = new MsgProcessor2(msgBuffer);
            try {
                // 处理数据
                processor.processMsg();
            } catch (Exception e) {
            }
        }).start();
        // 接下来，这里可以干任何事情
    }
}