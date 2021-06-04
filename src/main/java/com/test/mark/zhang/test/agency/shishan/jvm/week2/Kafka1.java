package com.test.mark.zhang.test.agency.shishan.jvm.week2;

public class Kafka1 {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            loadReplicasFromDisk();
            Thread.sleep(1000);
        }
    }

    private static void loadReplicasFromDisk() {
        //while里面循环创建对象,出栈后,对象可以被销毁,出栈,局部变量
        ReplicaManager replicaManager = new ReplicaManager();
        replicaManager.load();
    }
}
