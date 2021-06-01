package com.example.demo.com.test.test.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail testQuartz1() {
        return JobBuilder.newJob(TestTask1.class).withIdentity("testTask1").storeDurably().build();
    }
    //testTask1使用的固定间隔方式，testTask2使用的是cron表达式方式
    @Bean
    public Trigger testQuartzTrigger1() {
        //5秒执行一次
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(3)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(testQuartz1())
                .withIdentity("testTask1")
                .withSchedule(scheduleBuilder)
                .build();
    }
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

