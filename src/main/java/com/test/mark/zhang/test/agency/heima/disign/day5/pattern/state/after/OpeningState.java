package com.test.mark.zhang.test.agency.heima.disign.day5.pattern.state.after;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @version v1.0
 * @ClassName: OpeningState
 * @Description: 电梯开启状态类
 * @Author: 黑马程序员
 */
public class OpeningState extends LiftState {
    //当前状态要执行的方法
    @Override
    public void open() {
        System.out.println("电梯开启。。。");
    }

    @Override
    public void close() {
        //修改状态
        super.context.setLiftState(Context.CLOSING_STATE);
        //调用当前状态中的context中的close方法
        super.context.close();
    }

    @Override
    public void run() {
        //什么都不做
    }

    @Override
    public void stop() {
        //什么都不做
    }

    public static void main(String[] args) {
        //632160039655076
        //281000000
        //1623132988281   System.nanoTime()并不表示时间,可以用来计算时差
        System.out.println(System.nanoTime());
        System.out.println(LocalDateTime.now().getNano());
        System.out.println(System.currentTimeMillis());


        System.out.println(LocalDateTime.now().plusMinutes(5));

    }
}
