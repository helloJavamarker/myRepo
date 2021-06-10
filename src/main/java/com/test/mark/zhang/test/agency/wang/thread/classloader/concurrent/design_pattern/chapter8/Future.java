package com.test.mark.zhang.test.agency.wang.thread.classloader.concurrent.design_pattern.chapter8;

/***************************************
 * @author:Alex Wang
 * @Date:2017/3/22 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public interface Future<T> {

    T get() throws InterruptedException;

}