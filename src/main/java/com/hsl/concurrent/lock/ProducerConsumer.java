package com.hsl.concurrent.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 生产者消费者
 */
public class ProducerConsumer {

    public static void main(String[] args) {
        ProducerConsumer main = new ProducerConsumer();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 2; j++) {
                    main.get();
                }
            });
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                // 每隔一秒生产一个
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    main.put();
                }
            });
        }
    }
    private static final int cap = 10;
    private Semaphore semaphore = new Semaphore(cap);
    private volatile int count = 0;

    public boolean put() {
        boolean res = this.putVal();
        if (res) {
            semaphore.release();
        }
        return res;
    }

    public void get() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.getVal();
    }

    private synchronized boolean putVal() {
        if (count >= cap) {
            return false;
        }
        count++;
        System.out.println("生产值："+this.count);
        return true;
    }

    private synchronized void getVal() {
        count--;
        System.out.println("消费值："+this.count+1);
    }

    public int getCount() {
        return this.count;
    }
}
