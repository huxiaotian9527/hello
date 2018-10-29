package com.hu.base.jdk.thread.concurrent;

import java.util.concurrent.Semaphore;

/**
 * @Author hutiantian
 * @Date 2018/10/29 16:29:04
 */
public class SwapABCTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        new MyThread(semaphore,"A").start();
        new MyThread(semaphore,"B").start();
        new MyThread(semaphore,"C").start();
    }


    static class MyThread extends Thread{
        public Semaphore semaphore;
        public String str;

        public MyThread(Semaphore semaphore,String str){
            this.semaphore = semaphore;
            this.str = str;
        }

        public void run(){
            try {
                while(true){
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+": "+str);
                    Thread.sleep(1000);
                    semaphore.release();
                    Thread.sleep(1);
                }
            }catch (Exception e){
                System.out.println(Thread.currentThread().getName()+e);
            }


        }


    }
}
