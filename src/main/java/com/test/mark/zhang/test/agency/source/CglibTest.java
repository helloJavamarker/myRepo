package com.test.mark.zhang.test.agency.source;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * @author by mark
 * @Classname CglibTest
 * @Description TODO
 * @Date 2021/12/25 7:56 下午
 */
public class CglibTest {

    /**
     * jdk动态代理要求较多,使用更不方便,cglib更通用,所以一般使用cglib动态代理
     * 像dubbo+zk使用动态代理时,通过jdk动态代理生成的代理类的包名和原来的类的包名可能不同,所以一般也是使用cglib
     *
     * 注意代理类和原来的类是兄弟关系,不能相互强转,不是同一类型
     * @param args
     */
    public static void main(String[] args) {
        UserService target = new UserService();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback((MethodInterceptor) (object, method, args1, methodProxy) -> {
            if ("test".equals(method.getName())) {
                System.out.println("before");
            method.invoke(target, args1);
//            methodProxy.invoke(target, args1);//method和methodProxy区别?    method一般不用
//                methodProxy.invokeSuper(target, args1);  //视频说是会执行本身方法,不会走代理方法,但是这里会报错
                System.out.println("after");
                return null;
            }
            return method.invoke(target, args1);
        });

        UserService userService = (UserService) enhancer.create();
        userService.test();
    }

    //这里可以是类,也可以是接口
    public static class UserService {
        public void test() {
            System.out.println("test");
        }
    }
}
