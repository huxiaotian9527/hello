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
        for (int i = 0; i < 10; i++) {
            T task = new T();
            poolExecutor.execute(task);
            System.out.println("the num of pool is :"+poolExecutor.getPoolSize());
        }
        Thread.sleep(200);
    }

    static class T implements Runnable{
        public void run(){
//            while (true){
                System.out.println(System.currentTimeMillis()+": hahaha------");
                try {
                    Thread.sleep(500);
                }catch (Exception e){}
//            }

        }
    }
}
