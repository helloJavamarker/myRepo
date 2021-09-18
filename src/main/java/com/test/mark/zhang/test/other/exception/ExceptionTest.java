package com.test.mark.zhang.test.other.exception;

/**
 * @author by mark
 * @Classname ExceptionTest
 * @Description TODO
 * @Date 2021/9/14 4:07 下午
 */
public class ExceptionTest {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            try {
                int a = 10 / 2;
            } catch (Exception e) {
                //e.getStackTrace();
            }
        }
        System.out.println("time:" + (System.currentTimeMillis() - start));
    }
}
