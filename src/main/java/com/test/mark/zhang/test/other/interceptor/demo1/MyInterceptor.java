package com.test.mark.zhang.test.other.interceptor.demo1;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println(methodInvocation.getMethod().getName());
        return methodInvocation.proceed();
    }

    public static void main(String[] args) throws Throwable {
        MyInterceptor interceptor = new MyInterceptor();
        //interceptor.invoke(new CglibMethodInvocation());
    }
}

