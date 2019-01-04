package com.hu.base.jdk.thread.consumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @Author hutiantian
 * @Date 2019/1/3 16:32:48
 */
public class Producer implements Runnable{
    private BlockingQueue<String> blockingQueue;

    public Producer(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    public void run(){
        Random random = new Random();
        String str= random.nextInt(100)+"";
        try {
            Thread.sleep(Integer.parseInt(str)*10);
            blockingQueue.put(str);
        }catch (Exception e){
        }
        System.out.println(Thread.currentThread().getName()+":I am producer ,I put "+str);
    }
}
