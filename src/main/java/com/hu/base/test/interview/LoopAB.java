package com.hu.base.test.interview;

/**
 * @Author hutiantian
 * @Date 2020/3/4 20:03:02
 */
public class LoopAB {

    public static void main(String[] args) throws InterruptedException {
        Object lockA = new Object();
        Object lockB = new Object();
        Object lockC = new Object();
        new Thread(()->{
            synchronized(lockA){
                    while (true){
                        System.out.println("A");

                        lockB.notify();
                        try {
                            Thread.sleep(100);
                            lockA.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

            }
        }).start();
        Thread.sleep(100);
        new Thread(()->{
            synchronized(lockB){
                    while (true){
                        System.out.println("B");
                        lockC.notify();
                        try {
                            Thread.sleep(100);
                            lockB.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }

            }
        }).start();
        Thread.sleep(100);
        new Thread(()->{
            synchronized(lockC){
                    while (true){
                        System.out.println("C");
                        lockA.notify();
                        try {
                            Thread.sleep(100);
                            lockC.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                    }
                }

            }
        }).start();
    }
}
