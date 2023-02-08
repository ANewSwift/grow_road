package com.hsl.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantLock方法测试
 */
public class TestReentrantLock {

    public static void main(String[] args) {
        TestReentrantLock main = new TestReentrantLock();
//        main.testTryLock();
//        main.testLockInterruptibly();
        main.testFair();
    }

    /**
     * 测试tryLock
     */
    private void testTryLock() {
        ReentrantLock lock = new ReentrantLock();
        new Thread(() -> {
            try {
                System.out.println("t1 start");
                lock.lock();
                System.out.println("t1 get lock success");
                TimeUnit.SECONDS.sleep(10);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t1").start();


        new Thread(() -> {
            boolean getLock = false;
            try {
                System.out.println("t2 start");
                getLock = lock.tryLock(5, TimeUnit.SECONDS);
                if (getLock) {
                    System.out.println("t2 get lock success");
                } else {
                    System.out.println("t2 get lock fail");
                }
                TimeUnit.SECONDS.sleep(1);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (getLock) {
                    lock.unlock();
                }
            }
        },"t2").start();
    }

    /**
     * 测试lockInterruptibly
     * TODO
     */
    private void testLockInterruptibly() {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            System.out.println("t1 start");
            try {
                lock.lockInterruptibly();
                System.out.println("t1 get lock success");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("t1 end");
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        t1.interrupt();
    }

    /**
     * 测试公平锁与非公平
     */
    private void testFair() {
        ReentrantLock lock = new ReentrantLock(true);
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName()+"获得锁");
                } finally {
                    lock.unlock();
                }
            }
        },"t1").start();
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName()+"获得锁");
                } finally {
                    lock.unlock();
                }
            }
        },"t2").start();
    }
}
