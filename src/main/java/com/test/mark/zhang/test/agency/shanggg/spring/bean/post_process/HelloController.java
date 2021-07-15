package com.test.mark.zhang.test.agency.shanggg.spring.bean.post_process;

import org.springframework.stereotype.Controller;

@Controller
public class HelloController{
   
    @RoutingInjected
    private HelloService helloService;
    
    public void sayHello(){
        this.helloService.sayHello();
    }

    public void sayHi(){
        this.helloService.sayHi();
    }
}