package com.hsl.designpattern.structure.proxy;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        JdkProxy proxyHandler = new JdkProxy(new A());
        IA a = (IA) Proxy.newProxyInstance(
                IA.class.getClassLoader(),
                new Class[]{IA.class},
                proxyHandler);
        a.print("zhangsan");
    }
}
