package com.test.mark.zhang.controller.other;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("getWithList")
    public String getWithList(@ModelAttribute RequestBean bean) {
        System.out.println(bean);
        return "success";
    }

    @GetMapping("getWithListTest")
    public String getWithListTest(@RequestParam RequestBean bean) {
        System.out.println(bean);
        return "success";
    }


    @GetMapping("getWithListTest2")
    public String getWithListTest2(@RequestParam("name") String name, @RequestParam("age")int age, @RequestParam("hobbys")List<String> hobbys) {
        System.out.println(name);
        System.out.println(age);
        System.out.println(hobbys);
        return "success";
    }

    @PostMapping("post")
    public String getWithPost(@RequestBody RequestBean bean) {
        System.out.println(bean);
        return "postBean";
    }
}
