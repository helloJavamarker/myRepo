package com.test.mark.zhang.test.agency.tao.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConsumerDemo {
    private static final String SERVERS = "doitedu01:9092,doitedu02:9092,doitedu03:9092";

    public static void main(String[] args) throws InterruptedException {

        // Atomic 原子性的，
        AtomicBoolean isRunning = new AtomicBoolean(true);


        // 参数配置
        Properties props = new Properties();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVERS);

        // 自动设置读取的起始offset，值可以是： earliest， latest ，none
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // 自动提交消费位移offset
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);

        // 设置该消费者所属的组id
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "g1");


        // 开启一个新的线程去启动consumer消费数据
        Thread thread = new Thread(new ConsumerTask(props, isRunning));
        thread.start();


        // 主线程继续干别的工作
        Thread.sleep(60000);

        isRunning.set(false);

    }
}
