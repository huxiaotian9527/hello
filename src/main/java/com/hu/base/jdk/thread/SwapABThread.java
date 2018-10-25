package com.hu.base.jdk.thread;

/**
 * 两个交替打印的thread
 * @Author hutiantian
 * @Date 2018/10/24 18:25:58
 */
public class SwapABThread {

    public static void main(String[] args) throws Exception {
        Object obj = new Object();
        new Thread(){
            public void run(){
                synchronized(obj){
                    for (int i = 0; i < 5; i++) {
                        System.out.println("A");
                        obj.notify();
                        try {
                            obj.wait();
                        }catch (Exception e){

                        }
                    }
                    obj.notify();
                }
            }
        }.start();
        Thread.sleep(10);
        new Thread(){
            public void run(){
                synchronized(obj){
                    for (int i = 0; i < 5; i++) {
                        System.out.println("B");
                        obj.notify();
                        try {
                            obj.wait();
                        }catch (Exception e){

                        }
                    }
                }
            }
        }.start();
    }
}
