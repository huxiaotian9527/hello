package com.hu.base.jdk.thread;
/**
 * @author Toby
 * @version 1.0.0
 * @filename ThreadLocalDemo.java
 * @time 2017-11-1 下午10:17:00
 * @copyright(C) 2015 深圳市北辰德科技有限公司
 */
public class ThreadLocalDemo implements Runnable{
	//通过匿名类部类覆盖ThreadLocal种的initialValue()方法，指定初始值（不覆盖默认初始值为null）
	private static final ThreadLocal<Integer> localNum = new ThreadLocal<Integer>(){
		protected Integer initialValue() {
			return 0;
		}
	};

	public void run(){
		for(int i=0;i<100;i++){
			localNum.set(localNum.get()+1);
			if(i==50){
				System.gc();
			}
			System.out.println(Thread.currentThread().getName()+": "+localNum.get());
		}
	}

	public static void main(String[] args) {
		Runnable demo = new ThreadLocalDemo();
		new Thread(demo).start();
		new Thread(demo).start();
	}
}
