package com.hsl.designpattern.create.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Client {
    public static void main(String[] args) {
        Client client = new Client();
//        client.testHungry();
        client.testLazy();
    }

    public void testLazy() {
        Map<SingletonLazy,Object> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> map.put(SingletonLazy.getInstance(),new Object())).start();
        }
        System.out.println(map.size() == 1);
    }

    public void testHungry() {
        Map<SingletonHungry,Object> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> map.put(SingletonHungry.getInstance(),new Object())).start();
        }
        System.out.println(map.size() == 1);
    }
}
