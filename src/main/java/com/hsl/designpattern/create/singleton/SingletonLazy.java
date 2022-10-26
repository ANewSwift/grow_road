package com.hsl.designpattern.create.singleton;

public class SingletonLazy {
    private SingletonLazy(){};
    private static SingletonLazy INSTANCE;
    public static synchronized SingletonLazy getInstance1() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonLazy();
        }
        return INSTANCE;
    }
    public static SingletonLazy getInstance() {
        if (INSTANCE == null) {
            synchronized (SingletonLazy.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SingletonLazy();
                }
            }
        }
        return INSTANCE;
    }
}
