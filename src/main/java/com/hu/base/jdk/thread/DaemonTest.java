package com.hu.base.jdk.thread;

import java.io.IOException;

/**
 * @author Toby
 * @version 1.0.0
 * @filename DaemonTest.java
 * @time 2017-12-18 下午2:57:13
 * @copyright(C) 2015 深圳市北辰德科技有限公司
 */
public class DaemonTest extends Thread{

	public void run(){ 
		for(int i = 1; i <= 100;i++){ 
			try{ 
				Thread.sleep(100); 

			} catch (InterruptedException ex){ 
				ex.printStackTrace(); 
			} 
			System.out.println(i); 
		} 
	} 
	public static void main(String [] args){ 
		DaemonTest test = new DaemonTest(); 
		// 如果不设置daemon，那么线程将输出100后才结束 
		test.setDaemon(true); //在test未结束前，执行下面的输入操作，则test终止执行，因为jvm中只剩下守护线程时会终止
		test.start(); 
		System.out.println("isDaemon = " +test.isDaemon()); 
		try { 
			System.in.read(); //接受输入，使程序在此停顿，一旦接收到用户输入，main线程结束，守护线程自动结束 
		} catch (IOException ex) { 
			ex.printStackTrace(); 
		}
		System.out.print(Thread.currentThread().getName()+"结束");
	} 
}
