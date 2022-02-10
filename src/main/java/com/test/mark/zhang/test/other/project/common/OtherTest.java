package com.test.mark.zhang.test.other.project.common;

import java.math.BigDecimal;

/**
 * @author by mark
 * @Classname OtherTest
 * @Description TODO
 * @Date 2022/2/9 10:20 上午
 */
public class OtherTest {
    public static void main(String[] args) {

        //BigDecimal.ROUND_HALF_UP  四舍五入,
        System.out.println(new BigDecimal(345).divide(new BigDecimal(678), 4, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)));
        System.out.println(new BigDecimal(345).divide(new BigDecimal(678), BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)));
        System.out.println(new BigDecimal(345).divide(new BigDecimal(678), 4).multiply(BigDecimal.valueOf(100)));

        BigDecimal num1 = new BigDecimal(2.225667);//这种写法不允许，会造成精度损失

        BigDecimal num2 = new BigDecimal(2);//这种写法是可以的

        BigDecimal num = new BigDecimal("2.225667");//一般都会这样写最好
        int count = num.scale();
        System.out.println(count);//6 返回的是小数点后位数



        //枚举介绍
        BigDecimal b = new BigDecimal("2.225667").setScale(2, BigDecimal.ROUND_DOWN);
        System.out.println(b);//2.22 直接去掉多余的位数

        BigDecimal c = new BigDecimal("2.224667").setScale(2, BigDecimal.ROUND_UP);
        System.out.println(c);//2.23 跟上面相反，进位处理

        BigDecimal f = new BigDecimal("2.224667").setScale(2, BigDecimal.ROUND_CEILING);
        System.out.println(f);//2.23 如果是正数，相当于BigDecimal.ROUND_UP

        BigDecimal g = new BigDecimal("-2.225667").setScale(2, BigDecimal.ROUND_CEILING);
        System.out.println(g);//-2.22 如果是负数，相当于BigDecimal.ROUND_DOWN


        BigDecimal d = new BigDecimal("2.225").setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println("ROUND_HALF_UP"+d); //2.23  四舍五入（若舍弃部分>=.5，就进位）


        BigDecimal e = new BigDecimal("2.225").setScale(2, BigDecimal.ROUND_HALF_DOWN);
        System.out.println("ROUND_HALF_DOWN"+e);//2.22  四舍五入（若舍弃部分>.5,就进位）


    }
}
