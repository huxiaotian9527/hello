package com.hu.base.jdk.thread.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author hutiantian
 * @Date 2018/10/29 14:47:28
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
         CountDownLatch latch = new CountDownLatch(1);

        new Thread(){
            public void run() {
                try {
                    System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                    Thread.sleep(100000);
                    System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();

//        new Thread(){
//            public void run() {
//                try {
//                    System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
//                    Thread.sleep(300000);
//                    System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
//                    latch.countDown();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            };
//        }.start();

        try {
            Thread.sleep(1000);
            System.out.println("等待2个子线程执行完毕...");
            latch.await(100000, TimeUnit.SECONDS);
            System.out.println("2个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
