package com.hu.base.jdk.thread.Lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Toby
 * @version 1.0.0
 * @filename ReentrantLockTest.java
 * @time 2017-11-12 下午2:36:08
 * @copyright(C) 2015 深圳市北辰德科技有限公司
 */
public class ReentrantLockTest {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    public static ReentrantLockTest test = new ReentrantLockTest();
    private final Lock lock = new ReentrantLock();    //注意这个地方
    public static void main(String[] args)  {
         
        new Thread("Thread1"){
            public void run() {
                test.insert(Thread.currentThread());
            }
        }.start();
         
        new Thread("Thread2"){
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();
    }  
     
    public void insert(Thread thread) {
        lock.lock();
        try {
            System.out.println(thread.getName()+"得到了锁");
            for(int i=0;i<5;i++) {
                arrayList.add(i);
            }
            Thread.sleep(5000);
        } catch (Exception e) {
            // TODO: handle exception
        	System.out.println(thread.getName()+" in Exception");
        }finally {
            System.out.println(thread.getName()+"释放了锁");
            lock.unlock();
        }
    }
}