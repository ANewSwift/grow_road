package com.hsl.concurrent.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class TestCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(10, () -> {
            System.out.println("满人发车");
            for (int i = 1; i <= 3; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                try {
                    cb.await();
                    System.out.println(Thread.currentThread().getName() + "*** ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, "t"+i).start();
        }
    }
}
