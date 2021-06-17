package com.test.mark.zhang.service.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class KafkaService {

    public void sendMessage(String topic, String message) {
//        System.out.println("send kafka");
//        Properties properties = new Properties();
//        //Invalid url in bootstrap.servers: 127.0.0.1 网上说要给hosts配置映射
//        //如：172.24.110.110  kafka服务器主机名
//        properties.put("bootstrap.servers","127.0.0.1");
//        //properties.put("bootstrap.servers","127.0.0.1,localhost");
//        //下面两个配置必须加,不然会报错
//        //Missing required configuration "key.serializer" which has no default value.
//        properties.put("key.serializer", StringSerializer.class.getName());
//        properties.put("value.serializer", StringSerializer.class.getName());
//        properties.put("retries", 2);
//        properties.put("acks", "all");
//        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
//        try {
//            producer.send(new ProducerRecord<>(topic, message));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }
}
