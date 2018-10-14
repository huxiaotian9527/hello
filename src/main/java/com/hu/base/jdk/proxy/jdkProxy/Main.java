package com.hu.base.jdk.proxy.jdkProxy;

import com.hu.base.jdk.proxy.StudentService;
import com.hu.base.jdk.proxy.StudentServiceImp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author Toby
 * @version 1.0.0
 * @filename Main.java
 * @time 2017-10-29 12:06:50
 */
public class Main {
	public static void main(String[] args) {  
		StudentService userService = new StudentServiceImp();
		InvocationHandler invocationHandler = new com.hu.base.jdk.proxy.jdkProxy.MyInvocationHandler();
		StudentService userServiceProxy = (StudentService)Proxy.newProxyInstance(userService.getClass().getClassLoader(),  
				userService.getClass().getInterfaces(), invocationHandler);  
		System.out.println(userServiceProxy.getName());  
		System.out.println(userServiceProxy.getAge());  
	}
}
