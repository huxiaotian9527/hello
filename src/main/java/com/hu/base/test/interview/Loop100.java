package com.hu.base.test.interview;

/**
 * @Author hutiantian
 * @Date 2020/3/8 21:00:58
 */
public class Loop100 {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        new Thread(() -> {
            for (int i = 1;  i < 100 ; i=i+2){
                synchronized(lock){
                    lock.notify();
                    System.out.println(i);
                    try {
                        if(i<99){
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
        Thread.sleep(1);
        new Thread(() -> {
            for (int i = 2;  i < 101 ; i=i+2){
                synchronized(lock){
                    lock.notify();
                    System.out.println(i);
                    try {
                        if(i<100){
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

    }
}
