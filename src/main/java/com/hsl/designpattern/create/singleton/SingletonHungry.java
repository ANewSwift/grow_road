package com.hsl.designpattern.create.singleton;

public class SingletonHungry {
    private SingletonHungry() {}
    private static SingletonHungry instance = new SingletonHungry();
    public static SingletonHungry getInstance(){
        return instance;
    }
}
