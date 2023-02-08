package com.hsl.concurrent.nolock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 三种累加方式对比
 *
 * 测试结果：
 * LongAdder(604ms) > Synchronize(1410ms) > Atomic(2340ms)
 *
 * 10个线程
 * synchronized cost 3081ms, result = 100000000
 * atomic cost 2012ms, result = 100000000
 * longAdder cost 126ms, result = 100000000
 *
 * 100个线程
 * synchronized cost 2494ms, result = 100000000
 * atomic cost 2047ms, result = 100000000
 * longAdder cost 223ms, result = 100000000
 *
 * 1000个线程
 * synchronized cost 1691ms, result = 100000000
 * atomic cost 1703ms, result = 100000000
 * longAdder cost 170ms, result = 100000000
 *
 * 10000个线程
 * synchronized cost 1538ms, result = 100000000
 * atomic cost 2310ms, result = 100000000
 * longAdder cost 570ms, result = 100000000
 *
 * 100000个线程
 * synchronized cost 8884ms, result = 1000000000
 * atomic cost 21102ms, result = 1000000000
 * longAdder cost 5998ms, result = 1000000000
 */
public class TestAdd {

    int count1 = 0;
    AtomicLong count2 = new AtomicLong();
    LongAdder count3 = new LongAdder();
    final static int MaxThreads = 100000;
    final static int MaxCount = 10000;


    public static void main(String[] args) {
        TestAdd main = new TestAdd();
        main.testSynchronized();
        main.testAtomic();
        main.testLongAdder();
    }

    /**
     * 测试synchronized亿级累计耗时
     */
    private void testSynchronized() {
        Object lock = new Object();
        List<Thread> threads = new ArrayList<>(MaxThreads);
        for (int i = 0; i < MaxThreads; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < MaxCount; j++) {
                    synchronized (lock) {
                        count1++;
                    }
                }
            }));
        }
        long start = System.currentTimeMillis();
        threads.forEach(Thread::start);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long cost = System.currentTimeMillis() - start;
        System.out.println("synchronized cost " + cost + "ms, result = " + count1);
    }

    /**
     * 测试Atomic，亿级累加耗时
     */
    private void testAtomic() {
        List<Thread> threads = new ArrayList<>(MaxThreads);
        for (int i = 0; i < MaxThreads; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < MaxCount; j++) {
                    count2.incrementAndGet();
                }
            }));
        }
        long start = System.currentTimeMillis();
        threads.forEach(Thread::start);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long cost = System.currentTimeMillis() - start;
        System.out.println("atomic cost " + cost + "ms, result = " + count2.get());
    }

    /**
     * 测试Atomic，亿级累加耗时
     */
    private void testLongAdder() {
        List<Thread> threads = new ArrayList<>(MaxThreads);
        for (int i = 0; i < MaxThreads; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < MaxCount; j++) {
                    count3.increment();
                }
            }));
        }
        long start = System.currentTimeMillis();
        threads.forEach(Thread::start);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long cost = System.currentTimeMillis() - start;
        System.out.println("longAdder cost " + cost + "ms, result = " + count3.longValue());
    }
}
