package com.test.mark.zhang.controller.other;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticController {

    @Value("${zhang}")
    private String zhang;

    @Value("${zhang1}")
    private String zhang1;

    @GetMapping("/static")
    public String staticMethod() {
        System.out.println(zhang);
        System.out.println(zhang1);
        //StaticTest2.produceMsg(topic, msg);
        return "success";
    }

    @GetMapping("/static3")
    public String staticMethod3(@RequestParam("topic") String topic, @RequestParam("msg") String msg) {
        //StaticTest3.sendMsg(topic, msg);
        System.out.println(StringUtils.containsAny(zhang, "z"));
        return "success";
    }

    @GetMapping("/static4")
    public String staticMethod4(@RequestParam("topic") String topic, @RequestParam("msg") String msg) {
        //StaticTest4.send(topic, msg);
        return "success";
    }

}
