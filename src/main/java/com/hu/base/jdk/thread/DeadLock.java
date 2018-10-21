package com.hu.base.jdk.thread;

/**
 * 死锁demo
 *
 * @Author hutiantian
 * @Date 2018/10/19 16:09:29
 */
public class DeadLock {
    public static String obj1 = "obj1";
    public static String obj2 = "obj2";

    public static void main(String[] args) {
        new Thread("Thread1"){
            public void run() {
                synchronized(obj1){
                    System.out.println(Thread.currentThread().getName()+" : get obj1's lock!");
                    try{
                        Thread.sleep(100L);
                    }catch (Exception e){

                    }
                    synchronized (obj2){
                        System.out.println(Thread.currentThread().getName()+" : get obj2's lock!");
                    }
                }
            }
        }.start();
        new Thread("Thread2"){
            public void run(){
                synchronized(obj2){
                    System.out.println(Thread.currentThread().getName()+" : get obj2's lock!");
                    try{
                        Thread.sleep(100L);
                    }catch (Exception e){

                    }
                    synchronized (obj1){
                        System.out.println(Thread.currentThread().getName()+" : get obj1's lock!");
                    }
                }
            }
        }.start();


    }
}

