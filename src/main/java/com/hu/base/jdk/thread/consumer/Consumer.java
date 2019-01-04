package com.hu.base.jdk.thread.consumer;

import java.util.concurrent.BlockingQueue;

/**
 * @Author hutiantian
 * @Date 2019/1/3 16:32:59
 */
public class Consumer implements Runnable{
    private BlockingQueue<String> blockingQueue;

    public Consumer(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    public void run(){
        try {
            String str = blockingQueue.take();
            Thread.sleep(Integer.parseInt(str)*100);
            System.out.println(Thread.currentThread().getName()+":I am consumer ,I get "+str);
        }catch (Exception e){

        }
    }
}
