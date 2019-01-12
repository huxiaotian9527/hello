package com.hu.base.jdk.proxy.AgentInterface;

/**
 * @author hutiantian
 * @date: 2019/1/12 11:05
 * @since 1.0.0
 */
public class Test {
    public static void main(String[] args) {
        IHello hello = FacadeProxy.newMapperProxy(IHello.class);
        String str = hello.say("hello world");
        System.out.println(str);
    }
}
