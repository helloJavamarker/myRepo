package com.test.mark.zhang.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyTestController {

    @RequestMapping("hello")
    @ApiOperation("测试swagger")
    public String helloController(String name) {
        return "success::" + name;
    }
}
