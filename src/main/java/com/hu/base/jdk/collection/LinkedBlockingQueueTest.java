package com.hu.base.jdk.collection;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author hutiantian
 * @Date 2020/1/17 16:49:50
 */
public class LinkedBlockingQueueTest {
    public static void main(String[] args) {
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(10);
        linkedBlockingQueue.offer("ff");

        AtomicInteger count = new AtomicInteger();
        System.out.println(count.getAndIncrement());

    }
}
