package com.hu.base.jdk.thread;

/**
 * @Author hutiantian
 * @Date 2018/10/31 9:19:21
 */
public class AddExample {

    static int count = 0;

    public static void main(String[] args){
        for (int j = 0; j < 20000; j++) {
            new Thread(()->{count++;}).start();
        }
        System.out.println(count);
    }

}
