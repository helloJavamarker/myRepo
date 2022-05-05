package com.test.mark.zhang.test.agency.tao.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 带回调函数的producer示例
 */
public class ProducerCallbackDemo {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "doitedu01:9092,doitedu02:9092,doitedu03:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        for (int i = 0; i < 1000; i++) {
            ProducerRecord<String, String> rcd = new ProducerRecord<String, String>("tpc_1", "key" + i, "value" + i);
            producer.send(rcd, new Callback() {
                // 服务端给producer的发送线程返回响应时，就会调用该方法
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    // 如果响应是成功的，则recordMetadata是有值的
                    if (recordMetadata != null) {
                        System.out.println(recordMetadata.topic());
                        System.out.println(recordMetadata.partition());
                        System.out.println(recordMetadata.serializedKeySize());
                        System.out.println(recordMetadata.serializedValueSize());
                        System.out.println(recordMetadata.offset());
                    }
                }
            });
        }


    }
}
