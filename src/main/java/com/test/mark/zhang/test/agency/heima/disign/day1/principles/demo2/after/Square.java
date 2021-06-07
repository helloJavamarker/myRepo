package com.test.mark.zhang.test.agency.heima.disign.day1.principles.demo2.after;

/**
 * @version v1.0
 * @ClassName: Square
 * @Description: 正方形
 * @Author: 黑马程序员
 */
public class Square implements Quadrilateral {

    private double side;

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public double getLength() {
        return side;
    }

    @Override
    public double getWidth() {
        return side;
    }
}
