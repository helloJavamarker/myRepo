package com.test.mark.zhang.test.other.static0;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

public class StaticTest2 {
    static Properties properties;
    static {
        properties = new Properties();
        properties.put("bootstrap.servers","127.0.0.1");
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());
        properties.put("acks","all");
        properties.put("retries",0);
    }
    static KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
    public static void produceMsg(String topic, String msg) {
        try{
            Future<RecordMetadata> send = producer.send(new ProducerRecord<>(topic, msg));
            System.out.println(send);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void main(String[] args) {
        try{
            Future<RecordMetadata> send = producer.send(new ProducerRecord<>("topic", "msg"));
            //Failed to construct kafka producer,,,Invalid url in bootstrap.servers: 127.0.0.1
            System.out.println(send);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
