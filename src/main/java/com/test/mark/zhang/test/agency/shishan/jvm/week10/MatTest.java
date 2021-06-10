package com.test.mark.zhang.test.agency.shishan.jvm.week10;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname MatTest
 * @Description TODO
 * @Date 2021/6/9 7:32 下午
 * @Created by mark
 */
public class MatTest {
    public static void main(String[] args) throws InterruptedException {
        List<Data> datas =new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            datas.add(new Data());
        }
        Thread.sleep(1 * 60 * 60 * 1000);
    }

    static class Data {

    }
}
