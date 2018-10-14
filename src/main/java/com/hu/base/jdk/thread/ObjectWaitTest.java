package com.hu.base.jdk.thread;
/**
 * @author Toby
 * @version 1.0.0
 * @filename ObjectWaitTest.java
 * @time 2017-11-6 下午8:49:32
 * @copyright(C) 2015 深圳市北辰德科技有限公司
 */
public class ObjectWaitTest {
	public void waitTest(){
		synchronized(this){
			System.out.println("I am in synchronized method");
			try {
				this.wait();
			} catch (InterruptedException e) {
				System.out.println("catch it!");
			}
		}
		System.out.println("I am out of synchronized");
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(){
			public void run(){
				new ObjectWaitTest().waitTest();
			}
		};
		t1.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(t1.isAlive());
		t1.interrupt();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(t1.isAlive());
	}
}
