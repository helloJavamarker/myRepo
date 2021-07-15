package com.test.mark.zhang.test.agency.shanggg.spring.bean.post_process;

@RoutingSwitch("hello.switch")
public interface HelloService{

    @RoutingSwitch("A")
    void sayHello();

    void sayHi();
}