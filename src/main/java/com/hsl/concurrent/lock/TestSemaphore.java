package com.hsl.concurrent.lock;

import java.util.concurrent.Semaphore;

public class TestSemaphore {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(1, true);
        new Thread(()->{
            try {
                System.out.println("t1 start");
                s.acquire();
                System.out.println("t1 get success");
                Thread.sleep(3000);
                System.out.println("t1 do something end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();

        new Thread(()->{
            try {
                System.out.println("t1 start");
                s.acquire();
                System.out.println("t1 get success");
                Thread.sleep(1000);
                System.out.println("t2 do something end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();
    }
}
