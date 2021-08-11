package com.test.mark.zhang.test.other.quartz;

import org.springframework.context.annotation.Configuration;

//@configuration和@component作为配置类的不同:https://blog.csdn.net/long476964/article/details/80626930
@Configuration
public class QuartzConfig {

//    @Bean
//    public JobDetail testQuartz1() {
//        return JobBuilder.newJob(TestTask1.class).withIdentity("testTask1").storeDurably().build();
//    }
//    //testTask1使用的固定间隔方式，testTask2使用的是cron表达式方式
//    @Bean
//    public Trigger testQuartzTrigger1() {
//        //5秒执行一次
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(3)
//                .repeatForever();
//        return TriggerBuilder.newTrigger().forJob(testQuartz1())
//                .withIdentity("testTask1")
//                .withSchedule(scheduleBuilder)
//                .build();
//    }
//
//    @Bean
//    public JobDetail testQuartz2() {
//        return JobBuilder.newJob(TestTask2.class).withIdentity("testTask2").storeDurably().build();
//    }
//
//    @Bean
//    public Trigger testQuartzTrigger2() {
//        //cron方式，每隔5秒执行一次
//        return TriggerBuilder.newTrigger().forJob(testQuartz2())
//                .withIdentity("testTask2")
//                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
//                .build();
//    }


}

