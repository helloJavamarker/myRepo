package com.test.mark.zhang.test.agency.wang.thread.classloader.concurrent.juc.utils.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/16
 * QQ交流群:601980517，463962286
 ***************************************/
public class PhaserExample3 {
    private final static Random random = new Random(System.currentTimeMillis());

    /**
     * running
     * <p>
     * bicycle
     * <p>
     * long jump
     *
     * @param args
     */
    public static void main(String[] args) {
        final Phaser phaser = new Phaser(5);

        for (int i = 1; i < 5; i++)
            new Athletes(i, phaser).start();

        new InjuredAthletes(6, phaser).start();
    }

    static class InjuredAthletes extends Thread {
        private final int no;

        private final Phaser phaser;

        InjuredAthletes(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                sport(phaser, no + ": start running.", no + ": end running.");

                sport(phaser, no + ": start bicycle.", no + ": end bicycle.");

//                System.out.println("Oh shit, i am injured.");

                System.out.println("Oh shit, i am injured, i will be exited.");
                phaser.arriveAndDeregister();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Athletes extends Thread {

        private final int no;

        private final Phaser phaser;

        Athletes(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                sport(phaser, no + ": start running.", no + ": end running.");

                sport(phaser, no + ": start bicycle.", no + ": end bicycle.");

                sport(phaser, no + ": start long jump.", no + ": end long jump.");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void sport(Phaser phaser, String x, String x2) throws InterruptedException {
        System.out.println(x);
        TimeUnit.SECONDS.sleep(random.nextInt(5));
        System.out.println(x2);
        phaser.arriveAndAwaitAdvance();
    }
}
