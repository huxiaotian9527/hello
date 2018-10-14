package com.hu.base.jdk.proxy.cjlibProxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Toby
 * @version 1.0.0
 * @filename CglibProxy.java
 * @time 2017-10-29 12:14:59
 */
public class CglibProxy implements MethodInterceptor{
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {  
        System.out.println("++++++before " + methodProxy.getSuperName() + "++++++");  
        Object o1 = methodProxy.invokeSuper(o, args);  
        System.out.println("++++++after " + methodProxy.getSuperName() + "++++++");  
        return o1;  
    }  
}
