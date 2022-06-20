package com.test.mark.zhang.test.agency.tao.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * kafka的consumer对象是 ： “非线程安全的”
 * 不要在多个线程中操作同一个consumer对象
 */
public class ConsumerTask implements Runnable {

    Properties props = null;
    AtomicBoolean isRunning = null;

    public ConsumerTask(Properties props, AtomicBoolean isRunning) {
        this.props = props;
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        // 构造consumer实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // 订阅主题
        consumer.subscribe(Collections.singletonList("tpc_1"));


        // 拉取消息
        while (isRunning.get()) {

            // 每一次poll得到的结果中，可能包含多个topic的多个partition的消息
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(Long.MAX_VALUE));
            for (ConsumerRecord<String, String> record : records) {

                // do some process ！
                System.out.println(record.key() + ","
                        + record.value() + ","
                        + record.topic() + ","
                        + record.partition() + ","
                        + record.offset());
                System.out.println("----------------------闷骚的分割线----------------------------");

            }
        }
        consumer.close(Duration.ofMillis(1000));
    }
}
