package com.hu.base.test;

/**
 * @Author hutiantian
 * @Date 2018/9/10 16:52:28
 */
public class TestThread extends Thread{
    public static boolean flag = false;
    public void run(){
        Long begin = System.currentTimeMillis();
        while(!flag){
            System.out.println("---invisible---");
        }
        Long end = System.currentTimeMillis();
        System.out.println("time: "+(end-begin));
    }

    public static void main(String[] args) throws Exception{
        new TestThread().start();
        Thread.sleep(600);
        flag = true;
    }
}
