package com.hu.base.jdk.proxy.AgentInterface;

/**
 * @author hutiantian
 * @date: 2019/1/12 11:05
 * @since 1.0.0
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

public class FacadeProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args){
        System.out.println("接口方法调用开始");
        //执行方法
        System.out.println("method toGenericString:"+method.toGenericString());
        System.out.println("method name:"+method.getName());
        System.out.println("method args:"+args[0]);
        Type t = method.getAnnotatedReturnType().getType();
        System.out.println(t.getTypeName());
        System.out.println("接口方法调用结束");
        return "";
    }

    public static <T> T newMapperProxy(Class<T> mapperInterface) {
        ClassLoader classLoader = mapperInterface.getClassLoader();
        Class<?>[] interfaces = new Class[]{mapperInterface};
        FacadeProxy proxy = new FacadeProxy();
        return (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
    }
}