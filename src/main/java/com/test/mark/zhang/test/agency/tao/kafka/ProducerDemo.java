package com.test.mark.zhang.test.agency.tao.kafka;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * kafka生产者api开发示例
 */
public class ProducerDemo {
    private static final String SERVERS = "doitedu01:9092,doitedu02:9092,doitedu03:9092";

    public static void main(String[] args) throws Exception {


        /**
         * 配置生产者参数
         */
        Properties props = new Properties();

        // 配置方式1，容易将参数名写错
        // props.load(ProducerDemo.class.getClassLoader().getResourceAsStream("client.properties"));
        // props.put("bootstrap.servers", "doitedu01:9092,doitedu02:9092,doitedu03:9092");

        // 配置方式2，利用常量类，不容易写错，且容易记
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVERS);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 关闭producer的幂等性功能； 涉及到一个重要的原理知识：kafka的幂等性
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "false");


        /**
         * 构造生产者实例对象
         */
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        /**
         * 构造消息来发送
         */
        for (int i = 0; i < 10000000; i++) {
            ProducerRecord<String, String> msg = new ProducerRecord<>("doit18", i + "", i + " - " + RandomStringUtils.randomAlphabetic(4, 7));
            producer.send(msg);
            Thread.sleep(500);
        }

        producer.close();

    }
}
