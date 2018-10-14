package com.hu.base.jdk.thread.threadDemo;
/**
 * @author Toby
 * @version 1.0.0
 * @filename ImplementsRunnable.java
 * @time 2017-11-1 下午10:35:18
 * @copyright(C) 2015 深圳市北辰德科技有限公司
 */
public class ImplementsRunnable implements Runnable{
	private volatile int a = 0;
	public void run(){
		for(int i=0;i<10;i++){
			a = a+1;
			System.out.println(Thread.currentThread().getName()+": "+a);
		}
	}
	
	
	public static void main(String[] args) {
		Runnable runnable = new ImplementsRunnable();
		new Thread(runnable).start();
		new Thread(runnable).start();
	}
}
