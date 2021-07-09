package com.test.mark.zhang.test.other.project.cascade.eventbus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname TestBeanB
 * @Description TODO
 * @Date 2021/7/9 1:41 下午
 * @Created by mark
 */
@Component
public class TestBeanB {

    @Autowired
    private TestBeanA testBeanA;

    public TestBeanB() {
        System.out.println("【TestBeanB.默认构造器】");
    }
}
