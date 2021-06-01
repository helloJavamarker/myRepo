package com.example.demo.com.test.controller.kafka;

import com.example.demo.com.test.service.kafka.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private KafkaService kafkaService;

    // 发送消息
    @GetMapping("/kafka/normal1")
    public void sendMessage1(String message) {
        System.out.println("hello kafka");
        kafkaTemplate.send("topicxxx11", message);

    }

    @GetMapping("/kafka/normal2")
    public void sendMessage2(@RequestParam("topic") String topic, @RequestParam("message")String message) {
        kafkaService.sendMessage(topic ,message);
    }


}
