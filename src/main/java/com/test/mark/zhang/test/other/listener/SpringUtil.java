package com.test.mark.zhang.test.other.listener;


import org.springframework.context.ApplicationContext;

/**
 * @author by mark
 * @Classname SpringUtil
 * @Description TODO
 * @Date 2021/11/30 1:22 下午
 */
public class SpringUtil {

    private static ApplicationContext applicationContext = null;

    public static synchronized void setContext(ApplicationContext context) {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = context;
        }
    }
}
