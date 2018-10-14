package com.hu.base.jdk.thread.interrupt;
/**
 * @author Toby
 * @version 1.0.0
 * @filename Example3.java
 * @time 2017-11-6 下午5:39:44
 * @copyright(C) 2015 深圳市北辰德科技有限公司
 */
public class Example3 extends Thread{
	volatile boolean stop = false;  
	public static void main( String args[] ) throws Exception {  
		Example3 thread = new Example3();  
		System.out.println( "Starting thread..." );  
		thread.start();  
		Thread.sleep( 3000 );  
		System.out.println( "Asking thread to stop..." );  
		thread.stop = true;//如果线程阻塞，将不会检查此变量  
		thread.interrupt();  
		Thread.sleep( 3000 );  
		System.out.println( "Stopping application..." );  
		//System.exit( 0 );  
	}  

	public void run() {  
		while ( !stop ) {  
			System.out.println( "Thread running..." );  
			try {  
				Thread.sleep( 1000 );  
			} catch ( InterruptedException e ) {  
				System.out.println( "Thread interrupted..." );  
			}  
		}  
		System.out.println( "Thread exiting under request..." );  
	}  

}
