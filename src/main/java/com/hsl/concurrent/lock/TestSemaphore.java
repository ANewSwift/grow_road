package com.hsl.concurrent.lock;

import java.util.concurrent.Semaphore;

public class TestSemaphore {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(1, true);
        new Thread(()->{
            try {
                s.acquire();
                Thread.sleep(1000);
                System.out.println("do something end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();

        new Thread(()->{
            try {
                s.acquire();
                Thread.sleep(1000);
                System.out.println("do something end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();
    }
}
