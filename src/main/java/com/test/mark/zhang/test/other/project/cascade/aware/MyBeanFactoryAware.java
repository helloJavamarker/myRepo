package com.test.mark.zhang.test.other.project.cascade.aware;

import com.test.mark.zhang.test.agency.heima.disign.day6.framework.context.ApplicationContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author by mark
 * @Classname MyBeanFactoryAware
 * @Description TODO
 * @Date 2021/7/12 6:14 下午
 */
@Component
public class MyBeanFactoryAware implements BeanFactoryAware {

    private static ApplicationContext context;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//        this.
    }
}
