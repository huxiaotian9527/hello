package com.hu.base.jdk.proxy;
/**
 * @author Toby
 * @version 1.0.0
 * @filename StudentServiceImp.java
 * @time 2017-10-29 11:11:19
 */
public class StudentServiceImp implements StudentService{
	public String getName(){
		System.out.println("NOW IN NAME");
		return "toby";
	}
	public String getAge(){
		System.out.println("NOW IN GETAGE");
		return "24";
	}
	public void eat(){
		System.out.println("i am hugery");
	}
}
