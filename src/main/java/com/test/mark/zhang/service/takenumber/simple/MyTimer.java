package com.test.mark.zhang.service.takenumber.simple;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {

    public void simpleTimer() {
        //使用ScheduledExecutorService代替Timer吧
        //如需要实现在某个具体时间执行什么任务的话，Timer足以胜任；如需要实现每个星期六8点闹钟提醒之类的复杂任务，就需要用Quartz。
        //Timer有且仅有一个线程去执行定时任务，如果存在多个任务，且任务时间过长，会导致执行结果与预期不符。

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("我执行了"+"  "+System.currentTimeMillis());
            }
        },1000,2000);

    }

  public static void main(String[] args) {
      Timer timer = new Timer();
      timer.schedule(new TimerTask() {
          @Override
          public void run() {
              System.out.println("我执行了"+"  "+System.currentTimeMillis());
          }
      },1000,2000);
  }
}
