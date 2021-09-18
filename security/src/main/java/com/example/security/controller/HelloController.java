package com.example.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by mark
 * @Classname HelloController
 * @Description TODO
 * @Date 2021/9/10 1:14 下午
 */
@RestController(/*"/hello"*/)
public class HelloController {

    // 两个路径都是hello,访问的时候不能用hello/hello,而是直接/hello
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/admin/hello")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user/hello")
    public String user(@RequestParam() String name) {
        return "user:" + name;
    }

    //授权
    ///hello 是任何人都可以访问的接口
    ///admin/hello 是具有 admin 身份的人才能访问的接口
    ///user/hello 是具有 user 身份的人才能访问的接口
//    所有 user 能够访问的资源，admin 都能够访问
}
