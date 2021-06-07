package com.test.mark.zhang.test.agency.heima.disign.day1.singleton.demo8;


/**
 * @version v1.0
 * @ClassName: Singleton
 * @Description: 静态内部类方式
 * @Author: 黑马程序员
 */
public class Singleton {

    //为啥不用volatile修饰?
    private static boolean flag = false;

    //私有构造方法
    private Singleton() {
        synchronized (Singleton.class) {

            //判断flag的值是否是true，如果是true，说明非第一次访问，直接抛一个异常，如果是false的话，说明第一次访问
            //第一次创建后,后面就一直使用这个对象,所以并发问题可以不考虑??? 对象可能被回收,所以还是有并发问题
            // todo,和demo3的单例对比
            if (flag) {
                throw new RuntimeException("不能创建多个对象");
            }
            //将flag的值设置为true
            flag = true;
        }
    }

    //定义一个静态内部类
    private static class SingletonHolder {
        //在内部类中声明并初始化外部类的对象
        private static final Singleton INSTANCE = new Singleton();
    }

    //提供公共的访问方式
    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
