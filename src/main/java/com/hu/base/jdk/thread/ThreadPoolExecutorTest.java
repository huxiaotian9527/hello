package com.hu.base.jdk.thread;

import java.util.concurrent.*;

/**
 * @author hutiantian
 * @date: 2018/12/18 22:47
 * @since 1.0.0
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) throws Exception{
        BlockingQueue workQueue = new LinkedBlockingQueue(10);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,10,10,TimeUnit.MINUTES,workQueue);
        for (int i = 0; i < 5; i++) {
            T task = new T();
            poolExecutor.execute(task);
            System.out.println("the num of pool is :"+poolExecutor.getPoolSize());
        }
        Thread.sleep(200);
        for (int i = 0; i < 20; i++) {
            T task = new T();
            poolExecutor.execute(task);
            System.out.println("the num of pool is :"+poolExecutor.getPoolSize());
        }
    }

    static class T implements Runnable{
        public void run(){
            System.out.println("hahaha------");
            try {
                Thread.sleep(5000);
            }catch (Exception e){}
        }
    }
}
