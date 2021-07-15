package com.test.mark.zhang.test.agency.shanggg.spring.bean.ab;

import org.springframework.stereotype.Service;

/**
 * @author by mark
 * @Classname HelloServiceV1
 * @Description TODO
 * @Date 2021/7/13 7:48 下午
 */

@Service
public class HelloServiceV1 implements HelloService{
    @Override
    public void sayHello() {
        System.out.println("hello from V1");
    }

    @Override
    public void sayHi() {
        System.out.println("hi from v1");
    }
}
