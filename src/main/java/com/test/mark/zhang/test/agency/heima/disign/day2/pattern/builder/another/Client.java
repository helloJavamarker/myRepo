package com.test.mark.zhang.test.agency.heima.disign.day2.pattern.builder.another;

/**
 * @author by mark
 * @Classname Client
 * @Description TODO
 * @Date 2022/1/24 1:16 下午
 */
public class Client {
    public static void main(String[] args) {
        System.out.println(new Person.PersonBuilder("zhangsan", 123)
                .hobby("eat")
                .privince("henan")
                .create());
    }
}
