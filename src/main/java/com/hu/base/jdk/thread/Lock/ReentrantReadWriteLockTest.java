package com.hu.base.jdk.thread.Lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Toby
 * @version 1.0.0
 * @filename ReentrantReadWriteLockTest.java
 * @time 2017-11-12 下午7:47:30
 * @copyright(C) 2015 深圳市北辰德科技有限公司
 */
public class ReentrantReadWriteLockTest {
	private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    
    public static void main(String[] args)  {
        final ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();
         
        new Thread(){
            public void run() {
                rwl.readLock().lock();
                try {
                    long start = System.currentTimeMillis();
                    while(System.currentTimeMillis() - start <= 10) {
                        System.out.println(Thread.currentThread().getName()+"正在进行读操作");
                        Thread.sleep(1);
                    }
                    System.out.println(Thread.currentThread().getName()+"读操作完毕");
                } catch (Exception e){

                } finally {
                    rwl.readLock().unlock();
                }
            
            };
        }.start();
         
        new Thread(){
            public void run() {
                try{
                    test.get(Thread.currentThread());
                }catch (Exception e){

                }
            };
        }.start();
         
    }  
     
    public void get(Thread thread) throws Exception{
        rwl.writeLock().lock();
        try {
            long start = System.currentTimeMillis();
            while(System.currentTimeMillis() - start <= 10) {
                System.out.println(thread.getName()+"正在进行读操作");
                Thread.sleep(1);
            }
            System.out.println(thread.getName()+"读操作完毕");
        } finally {
            rwl.writeLock().unlock();
        }
    }
}
