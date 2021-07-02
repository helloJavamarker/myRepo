package com.test.mark.zhang.test.other.reflect;

/**
 * @Classname Apple
 * @Description TODO
 * @Date 2021/6/19 10:17 上午
 * @Created by mark
 */
public class Apple {
    private int price;

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "price=" + price +
                '}';
    }
}
