package com.test.mark.zhang.test.other.project.cascade.other;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

/**
 * @author by mark
 * @Classname SpringUtils
 * @Description 其实和hutool里面一样
 * @Date 2021/7/15 1:31 下午
 */
@Slf4j
public class SpringUtils {
    private static ApplicationContext applicationContext = null;
    public static void setApplicationContext(ApplicationContext applicationContext) {
//        SpringUtil
        setContext(applicationContext);
    }

    public static synchronized void setContext(ApplicationContext context) {
        if (SpringUtils.applicationContext == null) {
            SpringUtils.applicationContext = context;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }
}
