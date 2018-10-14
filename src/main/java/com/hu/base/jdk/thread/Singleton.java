package com.hu.base.jdk.thread;
/**
 * 双重加锁的单例模式
 * @author Toby
 * @version 1.0.0
 * @filename Singleton.java
 * @time 2017-10-16 下午8:23:38
 * @copyright(C) 2015 深圳市北辰德科技有限公司
 */
public class Singleton {
	private volatile static Singleton singleton;
	
	private Singleton(){}
	
	public static Singleton getInstance(){
		if(singleton == null){
			synchronized(singleton){
				if(singleton == null){
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
}
