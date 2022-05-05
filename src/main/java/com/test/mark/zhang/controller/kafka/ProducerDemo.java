package com.test.mark.zhang.controller.kafka;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.clients.admin.ConfigEntry;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

/**
 * @author by mark
 * @Classname ProducerDemo
 * @Description TODO
 * @Date 2022/4/28 10:08 上午
 */
public class ProducerDemo {

    private static final String SERVERS = "127.0.0.1:9092";

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
//        properties.load(ProducerDemo.class.getClassLoader().getResourceAsStream("client.properties"));
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, "");
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVERS);
        //是否保证幂等性
        properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "false");
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        producer.send(new ProducerRecord<>("aa", "bb"));

    }

    @Test
    public void test() {
        System.out.println(RandomStringUtils.randomAlphanumeric(10));
        System.out.println(RandomStringUtils.random(10));
        System.out.println(RandomStringUtils.randomNumeric(10));
        System.out.println(RandomStringUtils.randomAscii(10));
        //xzc3NzsZQc
        //𡝑䢬瓻󠇄𝈏娌䐛
        //7352510142
        //\!M1rMl_@w
    }
}
