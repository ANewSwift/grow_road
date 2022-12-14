package com.hsl.concurrent.base;

public class ThreadTest {
    public static void main(String[] args) {
        T t = new T();
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                t.a();
            }
        };
        new Thread(r1).start();
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                t.b();
            }
        };
        new Thread(r2).start();
    }
}

class T extends Thread {
    static synchronized void a() {
        System.out.println("start a");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("aaa");
    }

    static synchronized void b() {
        System.out.println("start b");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("bbb");
    }
}

class A extends Thread {
    A(String name) {
        this.name = name;
    }

    String name;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(name + i);
        }
    }
}

class B implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println("BBBB");
        }
    }
}

