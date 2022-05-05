package com.test.mark.zhang.test.agency.tao.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * 演示，poll到一批数据后，按照一个一个partition来消费
 */
public class ConsumerDemo2 {
    private static final String SERVERS = "doitedu01:9092,doitedu02:9092,doitedu03:9092";

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVERS);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "g1");


        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(props);
        TopicPartition tpc_1_0 = new TopicPartition("tpc_1", 0);
        TopicPartition tpc_2_1 = new TopicPartition("tpc_2", 1);

        // 订阅指定的主题的指定分区
        kafkaConsumer.assign(Arrays.asList(tpc_1_0, tpc_2_1));

        // 拉取消息，进行处理
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100000L));

            /*for (ConsumerRecord<String, String> record : records) {
                // 这样去遍历的话，开发人员无法预知下一条遍历到的record是哪个主题的哪个分区的
            }*/

            // 先处理tpc_1的0号分区的数据
            List<ConsumerRecord<String, String>> records1 = records.records(tpc_1_0);
            for (ConsumerRecord<String, String> rec : records1) {

                // 做一些业务处理

            }

            // 在处理tpc_2的1号分区的数据
            List<ConsumerRecord<String, String>> records2 = records.records(tpc_2_1);
            for (ConsumerRecord<String, String> rec : records2) {

                // 做一些业务处理

            }
        }
    }

}
