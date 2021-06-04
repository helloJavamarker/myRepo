package com.test.mark.zhang.service.takenumber.distributed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RedisTNService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public void set(String key, String value){
        redisTemplate.opsForValue().set(key, value);
        System.out.println(redisTemplate.opsForValue().get("myKey"));
    }

    //*代替任意数量的字符   ?代替一个字符
    @Scheduled(cron = "0 50 10 ? * MON-THU")
    public void testScheduled(){
        System.out.println("springScheduled run:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

}
