package com.hu.base.jdk.thread.threadDemo;
/**
 * @author Toby
 * @version 1.0.0
 * @filename MyThread.java
 * @time 2017-11-1 下午10:25:54
 * @copyright(C) 2015 深圳市北辰德科技有限公司
 */
public class ExtendsThread extends Thread{
	private int i =0;
	
	public ExtendsThread(String name){
		super(name);
	}
	
	public void run(){
		for(;i<20;i++){
			System.out.println(Thread.currentThread().getName()+": "+i);
		}
	}
	
	public static void main(String[] args) {
		new ExtendsThread("Thread1").start();
		new ExtendsThread("Thread2").start();
	}
}
