package com.test.mark.zhang.test.static0;

import com.test.mark.zhang.test.static0.static03.MyProducer;
import com.test.mark.zhang.test.static0.static03.MyProperties;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StaticTest4 {
    static MyProperties properties;

    @PostConstruct
    public void init() {
        properties = new MyProperties();
        properties.setName("lisi");
        properties.setAge(24);
    }
    static MyProducer producer = new MyProducer(properties);
    static KafkaConsumer<String,String> consumer;

    /**
     * 初始化消费者,指定参数和topic即可直接消费
     */
    /*@PostConstruct
    public void initConsumer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1");
        properties.put("group.id","topic01");
        properties.put("enable.auto.commit","false");
        properties.put("fetch.min.bytes",0);
        properties.put("request.timeout.ms",40000);
        properties.put("max.poll.records",2000);

        *//**
         * 生产者是序列化,消费者是反序列化
         *//*
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());
        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList("topic01"));
        //消费者不需要知道具体msg是啥,所以可以再init后直接消费
        //生产者需要将具体msg发送出去
    }*/


    public static void send(String topic, String msg) {
        producer.send(topic, msg);
    }
}
