package com.hsl.concurrent.base;

import java.util.Random;

public class CreateThread {
    public static void main(String[] args) throws InterruptedException {
        new MyThread().start();
        new Thread(new MyRun()).start();
        new Thread(() -> System.out.println("hello lambada")).start();
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("hello MyThread");
        }
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println("hello MyRun");
        }
    }


}
