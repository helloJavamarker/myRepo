package com.test.mark.zhang.controller.redis;

import com.test.mark.zhang.service.takenumber.distributed.RedisTNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("redis")
public class RedisController {
    @Autowired
    private RedisTNService redisService;

    @PostMapping("put")
    public String setRedis(@RequestParam("key")String key, @RequestParam("value")String value) {
        redisService.set(key, value);
        return "success";
    }

    @GetMapping("get")
    public String getRedis(@RequestParam("key")String key) {
        return redisService.get(key);
    }
}
