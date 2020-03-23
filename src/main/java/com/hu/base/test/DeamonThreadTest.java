package com.hu.base.test;

/**
 * @Author hutiantian
 * @Date 2020/3/5 9:20:18
 */
public class DeamonThreadTest {
    public static void main(String[] args) {
        Thread t = new Thread(){
            public void run(){
                while(true){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(System.currentTimeMillis()+":"+Thread.currentThread().getName()+"I am  out");
                }
            }
        };
        t.setDaemon(true);
        t.start();

        System.out.println(Thread.currentThread().getName()+":out");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
