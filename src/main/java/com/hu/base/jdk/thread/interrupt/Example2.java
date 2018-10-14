package com.hu.base.jdk.thread.interrupt;

/**
 * @author Toby
 * @version 1.0.0
 * @filename Example2.java
 * @time 2017-11-6 下午4:46:31
 * @copyright(C) 2015 深圳市北辰德科技有限公司
 */
public class Example2 extends Thread{
	volatile boolean stop = false;  

	public void run() {  
		while (!stop) {  
			System.out.println( "Thread is running..." );  
			long time = System.currentTimeMillis();  
			while ( (System.currentTimeMillis()-time < 1000) && (!stop) ) {  
			}  
		}  
		System.out.println( "Thread exiting under request..." );  
	}  

	public static void main( String args[] ) throws InterruptedException{  
		Example2 thread = new Example2();  
		System.out.println( "Starting thread..." );  
		thread.start();  
		Thread.sleep( 3000 );  
		System.out.println( "Asking thread to stop..." );  
		thread.stop = true;  
		Thread.sleep( 3000 );  
		System.out.println( "Stopping application..." );  
		//System.exit( 0 );  
	}  
}
