package com.test.mark.zhang.test.agency.shengsy.java8;



import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @Classname FunctionTest
 * @Description TODO
 * @Date 2021/6/4 3:21 下午
 * @Created by mark
 */
public class FunctionTest {
    public static void main(String[] args) {
        System.out.println("加:" + method(2, 3, Integer::sum));
        System.out.println("减:" + method(2, 3, (a, b) -> a - b));
        System.out.println("乘:" + method(2, 3, (a, b) -> a * b));
        System.out.println("除:" + method(6, 3, (a, b) -> a / b));

        System.out.println("-----------下面可以实现man变成superman,但是没必要-------------");
        Man man = new Man();
        man.setName("Normal");
        man.setIQ(100);
        System.out.println("man:" + man);
        System.out.println("superMan1: " + superMan(man, (man1 -> {
            Man superMan = new Man();
            superMan.setName("superMan");
            superMan.setIQ(200);
            return superMan;
        })));
        System.out.println("superman2:" + superMan(man, (man1 -> {
            man1.setIQ(300);
            man1.setName("spuerMan2");
            return man1;
        })));
        System.out.println(man);
    }

    private static int method(int a, int b, BiFunction<Integer, Integer, Integer> biFunction) {
        return biFunction.apply(a, b);
    }

    private static Man superMan(Man man, Function<Man, Man> function) {
        return function.apply(man);
    }

}
