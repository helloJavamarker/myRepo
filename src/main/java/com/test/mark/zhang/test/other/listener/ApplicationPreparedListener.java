package com.test.mark.zhang.test.other.listener;

import org.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @author by mark
 * @Classname ApplicationPreparedListener
 * @Description TODO
 * @Date 2021/11/30 1:17 下午
 */
public class ApplicationPreparedListener implements ApplicationListener<ApplicationPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        SpringUtil.setContext(event.getContext());
    }


    public static void main(String[] args) {
        long earlyTime = System.currentTimeMillis() - 300 * 1000;
        long hourTime = System.currentTimeMillis() - 86400 * 1000;
        System.out.println(new Date());
        System.out.println(new Date(earlyTime));
        System.out.println(new Date(hourTime));
    }
}
