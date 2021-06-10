package com.test.mark.zhang.test.agency.shishan.jvm.week10;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Classname OomTest
 * @Description TODO
 * @Date 2021/6/10 1:10 下午
 * @Created by mark
 */
public class OomTest {

    //对于oom,最好有Zabbix、Open-Falcon之类的监控平台,进行实时监控
    //这里可以先将enhancer缓存起来,下次可以直接使用,否则以后创建enhancer对象,会附带创建很多其他对象,调用多的时候可能导致oom
    private static Enhancer enhancer = null;
    public static void main(String[] args) {
        enhancer = new Enhancer();
        long  counter = 0;
        while (true) {
            System.out.println("创建了:" + counter++ + ",个Car对象");

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

    // 递归调用例子
    public void doLog() {
        try {
            // 业务逻辑
            log();
        } catch (Exception e) {
            log();
        }
    }

    private void log() {
        try {
            // 异常日志写入es
        } catch (Exception e) {
            //当有异常的时候,会一直递归调用自己,导致栈溢出
            log();
        }
    }

}
