package com.test.mark.zhang.test.agency.heima.disign.day1.principles.demo4.after;

/**
 * @version v1.0
 * @ClassName: HeiMaSafetyDoor
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 黑马程序员
 */
public class HeiMaSafetyDoor implements AntiTheft,Fireproof,Waterproof {
    @Override
    public void antiTheft() {
        System.out.println("防盗");
    }

    @Override
    public void fireproof() {
        System.out.println("防火");
    }

    @Override
    public void waterproof() {
        System.out.println("防水");
    }
}
