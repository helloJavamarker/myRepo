package com.example.demo.com.test.test.jvm.week2;

public class Kafka2 {
    //这里不会循环创建对象,但是static修饰的对象,生命周期太长,要综合考虑
    static ReplicaManager replicaManager = new ReplicaManager();
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            loadReplicasFromDisk();
            Thread.sleep(1000);
        }
    }

    private static void loadReplicasFromDisk() {
        replicaManager.load();
    }
}
