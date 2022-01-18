package com.test.mark.zhang.test.other.webservice;

/**
 * @author by mark
 * @Classname WebServiceImpl
 * @Description TODO
 * @Date 2021/12/27 10:22 上午
 */
public class WebServiceImpl implements WebInterface{
    @Override
    public String sayHi(String name) {
        return "hello:" + name;
    }
}
