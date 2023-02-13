package com.hsl.designpattern.structure.proxy;

public class A implements IA {

    @Override
    public String print(String s) {
        String res = "hello " + s;
        System.out.println(res);
        return res;
    }
}
