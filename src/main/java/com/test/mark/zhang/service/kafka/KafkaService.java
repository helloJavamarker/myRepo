package com.test.mark.zhang.service.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class KafkaService {

    //配置弄成静态比较好
//    private static Properties properties  = new Properties();
//    static  {
//        //properties  = new Properties();
//        //properties.put("bootstrap.servers","");
////        properties.put("bootstrap.servers","127.0.0.1,localhost");
//        properties.put("bootstrap.servers","localhost");
//        //下面两个配置必须加,不然会报错
//        //Missing required configuration "key.serializer" which has no default value.
//        properties.put("key.serializer", StringSerializer.class.getName());
//        properties.put("value.serializer", StringSerializer.class.getName());
//        properties.put("retries", 2);
//    }
//    private static KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

    public void sendMessage(String topic, String message) {
//        System.out.println("send kafka");
//        Properties properties = new Properties();
//        Invalid url in bootstrap.servers: 127.0.0.1 网上说要给hosts配置映射
//        如：172.24.110.110  kafka服务器主机名
//        properties.put("bootstrap.servers","");
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
//            //throw new RuntimeException(e);
//        }
    }
}
