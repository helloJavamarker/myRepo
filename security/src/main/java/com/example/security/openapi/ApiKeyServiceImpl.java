package com.example.security.openapi;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author by mark
 * @Classname ApiKeyServiceImpl
 * @Description TODO
 * @Date 2022/3/29 5:02 下午
 */
@Service
public class ApiKeyServiceImpl /*extends ServiceImpl<>*/ implements ApiKeyService {
    private static final Map<String, ApiKeyCache> APIKEY_CACHE = new ConcurrentHashMap<>();

    @Override
    public boolean valid(String apiKey) {
        //todo
        return false;
    }

    @EventListener
    public void init(ApplicationPreparedEvent event) {
        // get key from db
        //put apikey_cache
    }


}
