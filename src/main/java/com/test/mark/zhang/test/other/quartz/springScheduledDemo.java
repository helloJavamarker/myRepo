package com.test.mark.zhang.test.other.quartz;

import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@EnableSwagger2
public class springScheduledDemo {
    //使用这种方式要在启动类上加@EnableScheduling注解
//    @Scheduled(cron = "1/6 * * * * ?")
    public void testScheduled(){
        System.out.println("springScheduled run:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
}
