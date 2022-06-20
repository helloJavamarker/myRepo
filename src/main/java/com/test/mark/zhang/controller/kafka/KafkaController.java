package com.test.mark.zhang.controller.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.test.mark.zhang.service.kafka.KafkaService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public void sendMessage2(@RequestParam("topic") String topic, @RequestParam("message") String message) {

        kafkaService.sendMessage(topic, message);
    }

    @Test
    public void test1() {
        String name = "zhan San LIsI 张三  Lisk";
//        name.replace(true, 0, )
        System.out.println(StringUtils.replaceIgnoreCase(name, "li", ""));
    }


}
