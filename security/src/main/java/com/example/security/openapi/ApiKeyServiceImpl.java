package com.example.security.openapi;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    public static void main(String[] args) {

        String content = "SNMPv2-SMI::enterprises.28557.2.6.1.3.1.19.46 = Counter64: 589223312";
//        System.out.println(Integer.getInteger(content.substring(content.indexOf("Counter:"))));
//        System.out.println(content.substring(content.indexOf("Counter:")));
//        System.out.println(content.substring(content.indexOf("Counter:") + 8));
//        System.out.println(Integer.valueOf(content.substring(content.indexOf("Counter:") + 8)));
        long integer = Long.parseLong(content.substring(content.indexOf("Counter64:") + 11));
        System.out.println(integer);
        System.out.println(getFlow(integer));
//        System.out.println(Integer.getInteger((content.substring(content.indexOf("Counter:") + 7))));
//        System.out.println(Integer.getInteger((content.substring(content.indexOf("Counter:") + 8))));
//        System.out.println(Integer.getInteger((content.substring(content.indexOf("Counter:") + 9))));
    }

    private static double getFlow(long flow) {
        return NumberUtil.div(flow, 1024 * 1024 * 1024, 2);
    }


}
