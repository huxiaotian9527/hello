package com.hu.base.jdk.thread.consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author hutiantian
 * @Date 2019/1/3 16:33:27
 */
public class ConsumerPattern {
    public static void main(String[] args) {
        BlockingQueue queue = new LinkedBlockingQueue(10);
        for (int i = 0; i < 20; i++) {
            Producer producer = new Producer(queue);
            new Thread(producer).start();
        }
        for (int i = 0; i < 20; i++) {
            Consumer consumer  = new Consumer(queue);
            new Thread(consumer).start();
        }
    }
}
