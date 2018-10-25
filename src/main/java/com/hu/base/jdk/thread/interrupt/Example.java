package com.hu.base.jdk.thread.interrupt;

/**
 * @Author hutiantian
 * @Date 2018/10/24 18:27:53
 */
public class Example {

    public static void main(String[] args) throws  Exception{
        Thread A = new Thread() {
            public void run() {
                try {
                    while (true) {
                        System.out.println("哈哈，我还没被中断！！");
                        Thread.sleep(500l);//阻塞状态，线程被调用了interrupte（）方法，清除中断标志，抛出InterruptedException
                        //dosomething
                        boolean isIn = this.isInterrupted();
                        //运行状态，线程被调用了interrupte（）方法，中断标志被设置为true
                        //非阻塞状态中进行中断线程操作
//                        if (isInterrupted()) break;//退出循环，中断进程
                    }
                } catch (InterruptedException e) {//阻塞状态中进行中断线程操作
                    boolean isIn = this.isInterrupted();//退出阻塞状态，且中断标志被清除，重新设置为false，所以此处的isIn为false
                    System.out.println("fuck! "+e);
                    return;//退出run方法，中断进程
                }
            }
        };
        A.start();
        Thread.sleep(2000);
        A.interrupt();
    }
}
