package com.test.mark.zhang.test.other.project.v5;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author by mark
 * @Classname ApplicationRunnerTest
 * @Description TODO
 * @Date 2022/4/27 9:56 上午
 */
@Component
@Order(0)
public class ApplicationRunnerTest implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("applicationRunner running...");
    }
}
