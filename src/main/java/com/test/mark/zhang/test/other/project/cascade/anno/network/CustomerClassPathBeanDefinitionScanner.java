package com.test.mark.zhang.test.other.project.cascade.anno.network;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.stereotype.Component;

import java.util.Set;

//@Component
//public class CustomerClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {
//
//    public CustomerClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
//        super(registry, useDefaultFilters);
//    }
//
//    /**
//     * 扫描包路径，通过include和exclude等过滤条件，返回符合条件的bean定义
//     * 由于这个方法在父类是protected，所以只能通过继承类获取结果
//     *
//     * @param basePackages 需要扫描的包路径
//     */
//    @Override
//    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
//        return super.doScan(basePackages);
//    }
//}
