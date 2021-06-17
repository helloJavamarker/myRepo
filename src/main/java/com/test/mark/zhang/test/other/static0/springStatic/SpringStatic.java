package com.test.mark.zhang.test.other.static0.springStatic;

import com.test.mark.zhang.service.kafka.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringStatic {

    @Autowired
    private /*static*/ KafkaService kafkaService;

    public void test01() {
        kafkaService.sendMessage("topic", "msg");
        System.out.println("sout");
    }
}
