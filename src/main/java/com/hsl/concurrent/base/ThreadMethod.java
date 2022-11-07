package com.hsl.concurrent.base;

import java.util.Objects;
import java.util.Random;

public class ThreadMethod {

    public Object lock = new Object();
    public Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        ThreadMethod method = new ThreadMethod();
//        method.threadByOrder();
//        method.waitNotify();
//        method.joinMethod();
        Thread t = new Thread(() -> {
            System.out.println("start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("end");
        });
        t.start();
        t.start();
    }

    /**
     * 线程按顺序执行
     */
    public void threadByOrder() {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(new Random().nextInt(1000));
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
                Thread.sleep(new Random().nextInt(1000));
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                t2.join();
                Thread.sleep(new Random().nextInt(1000));
                System.out.println("t3 end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }

    public void joinMethod() {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(20000);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                t1.join(5000);
                System.out.println("t2 join after");
                Thread.sleep(new Random().nextInt(1000));
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                t1.join(10000);
                System.out.println("t3 join after");
                Thread.sleep(new Random().nextInt(1000));
                System.out.println("t3 end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();
        t3.start();
        try {
            Thread.sleep(1000);
            synchronized (t1) {
                t1.notifyAll();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void waitNotify() throws InterruptedException {
        Thread thread0 = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println(Thread.currentThread().getName() + "start");
                    lock.wait();
                    System.out.println(Thread.currentThread().getName() + "end");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "thread0");

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println(Thread.currentThread().getName() + "start");
                    lock.wait();
                    System.out.println(Thread.currentThread().getName() + "end");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "thread1");

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "start");
                lock.notifyAll();
                System.out.println(Thread.currentThread().getName() + "end");
            }
        }, "thread2");

        thread0.start();
        thread1.start();
        Thread.sleep(1000);
        thread2.start();
    }
}
