package com.test.mark.zhang.test.agency.shanggg.spring.bean.ab;

import org.springframework.stereotype.Service;

/**
 * @author by mark
 * @Classname HelloServiceV2
 * @Description TODO
 * @Date 2021/7/13 7:49 下午
 */
@Service
public class HelloServiceV2 implements HelloService{
    @Override
    public void sayHello() {
        System.out.println("hello form V2");
    }

    @Override
    public void sayHi() {
        System.out.println("hi from v2");
    }
}
