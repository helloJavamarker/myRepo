package com.test.mark.zhang.test.agency.wang.concurrency.chapter11;

/**
 * @author by mark
 * @Classname ExitCapture
 * @Description TODO
 * @Date 2022/4/25 2:37 下午
 */
public class ExitCapture {
    public static void main(String[] args) {
        // 添加钩子方法,可以让程序在发生异常而停止前的时候做另外的工作,但注意,如果使用kill -9的方式让程序停止,则不会执行钩子方法
        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            System.out.println("the application will be exit");
            notifyAndRelease();
        }));
        int i = 0;
        while (true) {
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            if (i>20) {
                //如果没有钩子方法,则程序发生异常后,会直接停止
                throw new RuntimeException("error happen");
            }
        }
    }

    private static void notifyAndRelease() {
        System.out.println("notify to the admin,send mail...");
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("do another thing,such as,release source,file,socket...");
    }
}
