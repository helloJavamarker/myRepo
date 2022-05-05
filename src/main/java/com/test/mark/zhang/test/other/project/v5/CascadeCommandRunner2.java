package com.test.mark.zhang.test.other.project.v5;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author by mark
 * @Classname CascadeCommandRunner2
 * @Description TODO
 * @Date 2022/4/27 9:49 上午
 */
@Component
@Order(2)
public class CascadeCommandRunner2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandRunner2 running");
    }
}
