package com.hsl.algorithm.link;

public interface Interface {
    int a = 10;
    int add(int b);
}

class B implements Interface{
    @Override
    public int add(int b) {
        return  a + b;
    }
}
