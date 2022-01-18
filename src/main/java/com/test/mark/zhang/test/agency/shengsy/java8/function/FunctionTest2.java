package com.test.mark.zhang.test.agency.shengsy.java8.function;

import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * @author by mark
 * @Classname FunctionTest2
 * @Description TODO
 * @Date 2021/12/5 4:56 下午
 */
public class FunctionTest2 {
    public static void main(String[] args) {
        FunctionTest2 functionTest2 = new FunctionTest2();
        System.out.println(functionTest2.apply(5, a -> a * a));
        System.out.println(functionTest2.apply(4, a -> a + a));

        System.out.println(".......");
        System.out.println(functionTest2.compute(2, value -> value * 3, value -> value * value));
        System.out.println(functionTest2.compute2(2, value -> value * 3, value -> value * value));
    }

    private int apply(int a, Function<Integer, Integer> function) {
        Objects.requireNonNull(a);// 这里a如果是null,则直接抛出空指针

        return function.apply(a);
    }


    private int compute(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        //compose先应用function2
        return function1.compose(function2).apply(a);
    }

    private int compute2(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        //andThen先应用func1
        return function1.andThen(function2).apply(a);
    }

}
