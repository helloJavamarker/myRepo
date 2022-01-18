package com.test.mark.zhang.test.agency.source;

import com.sun.tools.hat.internal.model.Root;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author by mark
 * @Classname CircleDependence
 * @Description TODO
 * @Date 2021/12/25 2:38 下午
 */
public class CircleDependence {

    @Test
    public void createBean() {
        System.out.println("...");
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        AService bean = (AService) beanFactory.doCreateBean("aService", new RootBeanDefinition(AService.class), null);
//        System.out.println(bean);
    }

    public static class AService {

    }
}
