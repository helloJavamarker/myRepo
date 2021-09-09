package com.test.mark.zhang.controller.other.adit;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author by mark
 * @Classname ControllerAdvice
 * @Description TODO
 * @Date 2021/8/28 1:40 下午
 */
@Component
@Aspect
@Slf4j
public class ControllerAdvice {

    //@Around("execution(public * *..*controller.*.*(..))")
//    public Object handle(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        Object result = null;
//        try {
//            Function<ProceedingJoinPoint, AbstractControllerHandler> build = AbstractControllerHandler.getBuild();
//            if (null == build) {
//                AbstractControllerHandler.registerBuildFunction(DefaultControllerHandler::new);
//            }
//            build = AbstractControllerHandler.getBuild();
//            AbstractControllerHandler controllerHandler = build.apply(proceedingJoinPoint);
//            if (null == controllerHandler) {
//                log.warn(String.format("The method(%s) do not be handle by controller handler.", proceedingJoinPoint.getSignature().getName()));
//                result = proceedingJoinPoint.proceed();
//            } else {
//                result = controllerHandler.handle();
//            }
//        } catch (Throwable throwable) {
//            RuntimeHealthIndicator.failedRequestCount++;
//            log.error(new Exception(throwable), "Unknown exception- -!");
//
//            throw throwable;
//        }

//        return result;
//    }
}