package com.test.mark.zhang.test.agency.shishan.jvm.week2;

public class Kafka3 {
      // Kafka的静态变量“fetcher”引用了ReplicaFetcher对象，这是长期需要驻留在内存里使用的
      // 这个对象会在年轻代里停留一会儿，但是最终会进入老年代
      private static ReplicaManager fetcher = new ReplicaManager();

      public static void main(String[] args) throws InterruptedException {
          loadReplicasFromDisk();

          while(true) {
              fetchReplicasFromRemote();
              Thread.sleep(1000);
          }
      }

      private static void fetchReplicasFromRemote() {
          fetcher.fetch();
      }

      private static void loadReplicasFromDisk() {
          ReplicaManager replicaManager = new ReplicaManager();
          replicaManager.load();
      }
}
