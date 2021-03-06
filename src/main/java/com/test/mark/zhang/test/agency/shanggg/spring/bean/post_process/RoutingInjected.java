package com.test.mark.zhang.test.agency.shanggg.spring.bean.post_process;

import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RoutingInjected{
    //作用类似于我们常用的Autowired，声明了该注解的属性将会被注入一个路由代理类实例
}