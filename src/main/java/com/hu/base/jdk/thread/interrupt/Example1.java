package com.hu.base.jdk.thread.interrupt;
/**
 * @author Toby
 * @version 1.0.0
 * @filename Example1.java
 * @time 2017-11-5 下午10:35:21
 * @copyright(C) 2015 深圳市北辰德科技有限公司
 */
class Example1 extends Thread {  
	boolean stop=false;  

	public void run() {  
		while(!stop){  
			System.out.println( "Thread is running..." );  
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			} 
		}  
		System.out.println("Thread exiting under request..." );  
	}
	
	public static void main( String args[] ) throws Exception {  
		Example1 thread = new Example1();  
		System.out.println( "Starting thread..." );  
		thread.start();  
		Thread.sleep(3000);  
		System.out.println( "Interrupting thread..." );  
		thread.interrupt();  
		Thread.sleep(3000);  
		System.out.println("Stopping application..." );  
		//System.exit(0);  
	}  
}  
