package com.test.mark.zhang.test.agency.heima.disign.day3.pattern.adapter.object_adapter;

/**
 * @version v1.0
 * @ClassName: SDCardImpl
 * @Description: 具体的SD卡
 * @Author: 黑马程序员
 */
public class SDCardImpl implements SDCard {

    @Override
    public String readSD() {
        String msg = "SDCard read msg ： hello word SD";
        return msg;
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("SDCard write msg ：" + msg);
    }
}
