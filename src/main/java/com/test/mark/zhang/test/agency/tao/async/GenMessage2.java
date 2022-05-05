package com.test.mark.zhang.test.agency.tao.async;

import java.util.Random;

/*** 上游：数据生成者 */
class GenMessage2 {
    MsgBuffer buffer;

    public GenMessage2(MsgBuffer buffer) {
        this.buffer = buffer;
    }

    public void genMsg() throws Exception {
        Random random = new Random();
        while (true) {
            // 模拟生成数据
            String msg = "msg " + random.nextInt(100);
            // 将生成的数据放入缓冲
            buffer.put(msg);
            // 继续处理其他事情 // ……
        }
    }
}