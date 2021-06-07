package com.test.mark.zhang.test.agency.heima.disign.day4.pattern.proxy.static_proxy;

/**
 * @version v1.0
 * @ClassName: TrainStation
 * @Description: 火车站类
 * @Author: 黑马程序员
 */
public class TrainStation implements SellTickets {

    public void sell() {
        System.out.println("火车站卖票");
    }
}
