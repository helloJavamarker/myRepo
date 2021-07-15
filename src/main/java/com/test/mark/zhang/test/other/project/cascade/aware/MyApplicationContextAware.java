package com.test.mark.zhang.test.other.project.cascade.aware;

import com.test.mark.zhang.test.other.delayQueue.priority.User;
import com.test.mark.zhang.test.other.project.org.OrgController;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * @author by mark
 * @Classname MyApplicationContextAware
 * @Description TODO
 * @Date 2021/7/12 6:13 下午
 */
@Component
public class MyApplicationContextAware implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    public static <T> T getBean(Class<T> clazz) {
        if (context == null) {
            System.out.println("applicationContext是空的");
            return null;
        } else {
            System.out.println("applicationContext不是空的");
            return context.getBean(clazz);
        }
    }

}
