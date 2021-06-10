package com.test.mark.zhang.test.agency.shishan.jvm.week10;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Classname MetaSpaceTest
 * @Description TODO
 * @Date 2021/6/10 10:40 上午
 * @Created by mark
 */
public class MetaSpaceTest {

    //-XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
    public static void main(String[] args) {
        long  counter = 0;
        while (true) {
            System.out.println("创建了:" + counter++ + ",个Car对象");
            Enhancer enhancer = new Enhancer();
            //通过CGLIB的Enhancer类生成了一个Car类的子类
            enhancer.setSuperclass(Car.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                //动态创建类
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    if ("run".equals(method.getName())) {
                        System.out.println("启动汽车之前,先进行安全检查");
                        return methodProxy.invokeSuper(o, objects);
                    } else {
                        return methodProxy.invokeSuper(o, objects);
                    }
                }
            });
            Car car = (Car) enhancer.create();
            car.run();
        }
    }

    static class Car {
        public void run() {
            System.out.println("启程启动,开始行驶.......");
        }
    }

    //手动创建类
    static class SafeCar extends Car {
        @Override
        public void run() {
            System.out.println("安全启动....");
            super.run();
        }
    }
}
