package com.example.demo.com.test.controller;

import com.example.demo.com.test.test.static0.StaticTest2;
import com.example.demo.com.test.test.static0.StaticTest3;
import com.example.demo.com.test.test.static0.StaticTest4;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticController {

    @GetMapping("/static")
    public String staticMethod(@RequestParam("topic")String topic, @RequestParam("msg")String msg) {
        StaticTest2.produceMsg(topic, msg);
        return "success";
    }

    @GetMapping("/static3")
    public String staticMethod3(@RequestParam("topic")String topic, @RequestParam("msg")String msg) {
        StaticTest3.sendMsg(topic, msg);
        return "success";
    }

    @GetMapping("/static4")
    public String staticMethod4(@RequestParam("topic")String topic, @RequestParam("msg")String msg) {
        StaticTest4.send(topic, msg);
        return "success";
    }

}
