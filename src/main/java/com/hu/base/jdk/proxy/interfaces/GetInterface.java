package com.hu.base.jdk.proxy.interfaces;

import com.hu.base.jdk.proxy.StudentServiceImp;

/**
 * @author Toby
 * @version 1.0.0
 * @filename GetInterface.java
 * @time 2017-10-29 11:09:21
 */
public class GetInterface {
	public static void main(String[] args) {
		Class<?>[] interfaces = StudentServiceImp.class.getInterfaces();
		System.out.println(interfaces[0].getName());
	}
}
