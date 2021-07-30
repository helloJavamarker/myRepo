package com.test.mark.zhang.test.other.interceptor.demo1;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterceptorConfig {
   //注意该地址为项目具体包地址
   public static final String traceExecution = "execution(* com.test.mark.zhang.test.other.interceptor..*.*(..))";
   @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor2() {
        MyInterceptor interceptor = new MyInterceptor();
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(traceExecution);

        // 配置增强类advisor
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;
    }
}

