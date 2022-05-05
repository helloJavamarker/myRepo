package com.test.mark.zhang.test.agency.tao.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * kafka的内部事务控制机制api演示
 */
public class TransactionDemo {
    private static final String BROKERS = "doitedu01:9092,doitedu02:9092,doitedu03:9092";
    private static final String GROUPID = "doit18_tx_group";

    public static void main(String[] args) {

        //  构造一个消费者，去拉取数据
        Properties props_c = new Properties();
        props_c.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKERS);
        props_c.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props_c.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props_c.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props_c.put(ConsumerConfig.GROUP_ID_CONFIG, GROUPID);
        props_c.put(ConsumerConfig.CLIENT_ID_CONFIG, "c00001");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props_c);
        consumer.subscribe(Arrays.asList("doit18"));


        // 构造一个生产者，用来输出处理结果
        Properties props_p = new Properties();
        props_p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props_p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props_p.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "doit18_tx_001");  // 设置了事务号
        props_p.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);  // 开启幂等性
        props_p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKERS);

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props_p);

        // 初始化事务
        producer.initTransactions();

        while (true) {
            // 消费者拉取一批数据
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

            // 获取本消费者所被分配到的分区
            Set<TopicPartition> assignment = consumer.assignment();

            // 如果本次没有拉取到任何数据，则继续下一次循环
            if (records.isEmpty()) continue;

            try {
                // 开启事务
                producer.beginTransaction();

                // 构造一个记录分区->偏移量 的hashmap
                HashMap<TopicPartition, OffsetAndMetadata> offsetsMap = new HashMap<>();

                // 遍历每一个分区，来处理分区内的数据
                for (TopicPartition topicPartition : assignment) {
                    // 获取当前指定分区的数据
                    List<ConsumerRecord<String, String>> partitionRecords = records.records(topicPartition);
                    // 遍历分区中的每一条消息，做一些业务逻辑处理
                    for (ConsumerRecord<String, String> record : partitionRecords) {
                        // 假设业务逻辑： 将原数据的value变成大写
                        String newValue = record.value().toUpperCase();
                        // 根据处理后的数据，构造一条新的消息
                        ProducerRecord<String, String> resultRecord = new ProducerRecord<>("doit18_res", record.key(), newValue);
                        // 用producer将新的结果消息写出
                        producer.send(resultRecord);
                    }
                    // 在hashmap中记录本分区的消费偏移量
                    long lastConsumedOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                    offsetsMap.put(topicPartition, new OffsetAndMetadata(lastConsumedOffset + 1));
                }
                // 提交偏移量
                producer.sendOffsetsToTransaction(offsetsMap, GROUPID);
                // 提交事务
                producer.commitTransaction();
            } catch (Exception e) {
                // 如果有异常，则放弃事务
                producer.abortTransaction();
            }
        }
    }
}
