package com.test.mark.zhang.test.agency.heima.disign.day3.pattern.factory.config_factory;

/**
 * @version v1.0
 * @ClassName: Client
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 黑马程序员
 */
public class Client {
    public static void main(String[] args) {
        Coffee coffee = CoffeeFactory.createCoffee("american");
        System.out.println(coffee.getName());

        System.out.println("=============");
        Coffee latte = CoffeeFactory.createCoffee("latte");
        System.out.println(latte.getName());
    }
}
