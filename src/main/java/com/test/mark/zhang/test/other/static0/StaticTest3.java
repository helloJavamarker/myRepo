package com.test.mark.zhang.test.other.static0;

import com.test.mark.zhang.test.other.static0.static03.MyProducer;
import com.test.mark.zhang.test.other.static0.static03.MyProperties;

public class StaticTest3 {
    static MyProperties properties;
    {
        properties = new MyProperties();
        properties.setAge(23);
        properties.setName("zhangsan");
    }
    // Value 'properties' is always 'null'
    static MyProducer producer = new MyProducer(properties);

    public static void main(String[] args) {
        producer.send("topic1","msg1");
    }

    public static void sendMsg(String topic, String msg) {
        producer.send(topic, msg);
    }
}
