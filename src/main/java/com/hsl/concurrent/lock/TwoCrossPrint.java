package com.hsl.concurrent.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 两个线程交替打印
 * 线程一打印A~Z，线程二打印1-26，最后输出结果A1B2...Z26
 */
public class TwoCrossPrint {
    public static void main(String[] args) {
        TwoCrossPrint main = new TwoCrossPrint();
//        main.waitAndNotify();
        main.useLockSupport();
    }

    Thread t1;
    Thread t2;
    private void useLockSupport() {
        t1 = new Thread(() -> {
            char c = 'A';
            for (int i = 0; i < 26; i++) {
                System.out.print(c++);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                LockSupport.park();
                System.out.print(i+1);
                LockSupport.unpark(t1);
            }
        }, "t2");

        t1.start();
        t2.start();
    }

    private void waitAndNotify() {
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                char c = 'A';
                for (int i = 0; i < 26; i++) {
                    System.out.print(c++);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
//            cd.countDown();
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i <= 26; i++) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print(i);
                    lock.notify();
                }
            }
//            cd.countDown();
        }, "t2");

        t2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();

//        try {
//            cd.await();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}
