package com.hsl.designpattern.structure.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkProxy implements InvocationHandler {

    private Object proxiedObject;

    public JdkProxy(Object proxiedObject) {
        this.proxiedObject = proxiedObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("pre args = " + args);
        Object o =  method.invoke(proxiedObject, args);
        System.out.println("post args = " + args);
        return o;
    }

}
