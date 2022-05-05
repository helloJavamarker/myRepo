package com.test.mark.zhang.test.agency.tao;

import java.util.Random;

public class TestRandom {
    public static void main(String[] args) {
        Random random = new Random();
        int i = random.nextInt(10);
        System.out.println(i);

        double v = ((double) random.nextInt(10) / 10.0) * 2000;
        System.out.println((long) v);
    }
}
