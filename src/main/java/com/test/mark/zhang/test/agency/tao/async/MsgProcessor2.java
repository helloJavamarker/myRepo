package com.test.mark.zhang.test.agency.tao.async;

/*** 下游：数据处理者 */
class MsgProcessor2 {
    MsgBuffer buffer;

    public MsgProcessor2(MsgBuffer buffer) {
        this.buffer = buffer;
    }

    public void processMsg() throws Exception {
        while (true) { // 从缓冲池中获取数据
            String msg = buffer.take();
            // 将收到的消息进行合法性检查
            // 将检查合格的消息按照确定规范进行格式化处理
            // 根据消息中的某指定字段去数据库中查找匹配信息
            // 将匹配到的信息添加到“消息"中
            // ……
        }
    }
}