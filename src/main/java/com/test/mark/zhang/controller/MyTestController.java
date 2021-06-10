package com.test.mark.zhang.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyTestController {

    @RequestMapping("hello")
    @ApiOperation("测试swagger")
    public String helloController(String name) {
        return "success::" + name;
    }

    //post请求也能使用requestParam
    @PostMapping("hello2")
    public String hello2(@RequestParam("content") String content,@RequestParam("content2") String content2 ) {
        return "success";
    }
}
