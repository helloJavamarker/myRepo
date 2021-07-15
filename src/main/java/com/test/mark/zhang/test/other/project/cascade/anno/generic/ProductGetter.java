package com.test.mark.zhang.test.other.project.cascade.anno.generic;

import cn.hutool.core.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by mark
 * @Classname ProductGetter
 * @Description 抽奖器
 * @Date 2021/7/11 8:51 下午
 */
public class ProductGetter<T> {
    private T product;
    List<T> list = new ArrayList<>();
    public void addProduct(T t) {
        list.add(t);
    }

    //抽奖
    public T getProduct() {
        return list.get(RandomUtil.randomInt(list.size()));
    }

    public static void main(String[] args) {
        ProductGetter<String> getter = new ProductGetter<>();
        String[] products = {"apple","huawei","xiaomi","money"};
        Integer[] moneys = {1000,200,50,10};
        for (String product : products) {
            getter.addProduct(product);
        }
        System.out.println(getter.getProduct());
    }


    public <E> E getProduct(List<E> list) {
        return list.get(RandomUtil.randomInt(list.size()));
    }
}
