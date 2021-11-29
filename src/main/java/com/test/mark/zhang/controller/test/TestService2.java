package com.test.mark.zhang.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * @author by mark
 * @Classname TestService2
 * @Description TODO
 * @Date 2021/11/15 10:48 上午
 */
@Service
@DependsOn("testService1")
public class TestService2 {
//    @Autowired
//    private TestService1 testService1;

    public void test2() {

    }
}
