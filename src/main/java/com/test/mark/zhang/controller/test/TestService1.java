package com.test.mark.zhang.controller.test;

import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author by mark
 * @Classname TestService1
 * @Description TODO
 * @Date 2021/11/15 10:48 上午
 */
@Service
//@DependsOn("testService2")
//@Lazy
public class TestService1 {
//    @Autowired
//    private TestService2 testService2;

    @Async
    public void test1() {

    }
}
