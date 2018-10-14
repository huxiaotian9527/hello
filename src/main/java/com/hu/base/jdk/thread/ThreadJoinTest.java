package com.hu.base.jdk.thread;
/**
 * @author Toby
 * @version 1.0.0
 * @filename ThreadJoin.java
 * @time 2017-11-4 下午8:24:36
 * @copyright(C) 2015 深圳市北辰德科技有限公司
 */
public class ThreadJoinTest implements Runnable{
	public void run(){
		System.out.println("test jion");
		for(int i=0;i<5;i++){
			try{
				Thread.sleep(1000);
			}catch(Exception e){}
			System.out.println("睡眠："+i);
		}
		System.out.println("test jion finished");
	}
	public static void main(String[] args) {
		Thread t = new Thread(new ThreadJoinTest());
		Long stime = System.currentTimeMillis();
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis()-stime);
		System.out.println("main finished");
	}
}
