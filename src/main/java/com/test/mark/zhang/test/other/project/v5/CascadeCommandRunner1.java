package com.test.mark.zhang.test.other.project.v5;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author by mark
 * @Classname CascadeCommandRunner
 * @Description TODO
 * @Date 2022/4/27 9:43 上午
 */
@Component
@Order(1)
public class CascadeCommandRunner1 implements CommandLineRunner {
    /**
     * 在我们实际工作中，总会遇到这样需求，在项目启动的时候需要做一些初始化的操作，比如初始化线程池，提前加载好加密证书等。今天就给大家介绍一个 Spring Boot 神器，专门帮助大家解决项目启动初始化资源操作。
     * 这个神器就是 CommandLineRunner，CommandLineRunner 接口的 Component 会在所有 Spring Beans 都初始化之后，SpringApplication.run() 之前执行，非常适合在应用程序启动之初进行一些数据初始化的工作。
     *
     * 在实践中，使用ApplicationRunner也可以达到相同的目的，两着差别不大。
     *
     * 通过order控制顺序,order越小,启动越早
     * @param args
     * @throws Exception
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(String... args) throws Exception {

        //SpringApplication.run(DemoApplication.class, args); 后执行
        System.out.println("CommandRunner1 running");
        // 检查配置
        // 发布事件
        // 加锁
        // 更新配置
        // 释放锁
    }
}
