package com.test.mark.zhang.test.other.project.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author by mark
 * @Classname IpSyncAspect
 * @Description TODO
 * @Date 2021/7/12 7:12 下午
 */

@Component
@Aspect
public class IpSyncAspect {
//    @Autowired
//    private service service;

    @Autowired
    private AspectController controller;

    @Pointcut("@annotation(com.test.mark.zhang.test.other.project.aspect.IpSync)")
    public void sync() {
        
    }
    
    @Around("sync()&& @annotation(ipSync)")
    public Object process(ProceedingJoinPoint jp, IpSync ipSync) {
        Object result = null;
        try {
            System.out.println(AspectController.data);
            controller.clear();
            result = jp.proceed();
            System.out.println("============================");
            controller.add("li22");
            System.out.println(result);
            System.out.println(AspectController.data);
            handlerIpSyncEvent(jp, result, ipSync);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    private void handlerIpSyncEvent(ProceedingJoinPoint jp, Object result, IpSync ipSync) {
        
    }


}
