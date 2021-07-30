package com.test.mark.zhang.test.other.project.cache;

import com.test.mark.zhang.cache.collection.SecurityZoneCache;
import com.test.mark.zhang.test.other.project.security.SecurityZone;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author by mark
 * @Classname CacheAspect
 * @Description TODO
 * @Date 2021/7/21 9:18 上午
 */
@Aspect
@Component
public class CacheAspect {
    @Autowired
    private SecurityZoneController controller;
    
    private SecurityZoneCache cache = SecurityZoneCache.getInstance();
    
    @Pointcut("@annotation(com.test.mark.zhang.test.other.project.cache.CacheUpdate)")
    public void pointCut() {
    }
    
    @Around("pointCut()&&@annotation(CacheUpdate)")
    public Object process(ProceedingJoinPoint pj) {
        Object result = null;
        try {
            System.out.println(controller.getAll());
            result = pj.proceed();
            System.out.println(controller.getAll());



        }  catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    private void load() {
        loadData(() -> controller.generateData());
    }

    private void clear() {

    }



    //线程池 执行更新操作

    private <T> T loadData(CacheLoader<T> loader) {
        return loader.load();
    }
    
}
