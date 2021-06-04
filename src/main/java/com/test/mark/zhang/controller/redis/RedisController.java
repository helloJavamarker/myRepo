package com.test.mark.zhang.controller.redis;

import com.test.mark.zhang.service.takenumber.distributed.RedisTNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
    @Autowired
    private RedisTNService redisService;

    @PostMapping("redis")
    public String setRedis(@RequestParam("key")String key, @RequestParam("value")String value) {
        redisService.set(key, value);
        return "success";
    }
}
