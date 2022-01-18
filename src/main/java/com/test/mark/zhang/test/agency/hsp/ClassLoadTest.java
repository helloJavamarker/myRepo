package com.test.mark.zhang.test.agency.hsp;

import java.util.Scanner;

/**
 * @author by mark
 * @Classname ClassLoadTest
 * @Description TODO
 * @Date 2021/12/30 9:04 上午
 */
public class ClassLoadTest {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        switch (scanner.next()) {
            case "1" : {
                // 静态编译,这里使用javac编译.java代码时,会报错,所谓静态编译,就是在编译期就会报错
//                AA aa = new AA();
                System.out.println("aa");
            } case "2" :{
                //动态编译: 运行期才会报错
                Class<?> clazz = Class.forName("com.BB");
                Object o = clazz.newInstance();
                System.out.println("reflect");
            } default:{
                System.out.println("...");
            }
        }
    }
}
