package com.hu.base.jdk.proxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Toby
 * @version 1.0.0
 * @filename MyInvocationHandler.java
 */
public class MyInvocationHandler implements InvocationHandler{
//	private Object target;  
//
//	MyInvocationHandler(Object target) {  
//		this.target = target;  
//	} 

	public Object invoke(Object o, Method method, Object[] args) throws Throwable {  
		if("getName".equals(method.getName())){  
			System.out.println("before " + method.getName());  
			Object result = method.invoke(o, args);  
			System.out.println("after " + method.getName());  
			return result;  
		}else{  
			Object result = method.invoke(o, args);  
			return result;  
		}  

	}  
}
