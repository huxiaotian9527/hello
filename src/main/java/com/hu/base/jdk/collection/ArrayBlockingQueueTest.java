package com.hu.base.jdk.collection;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author hutiantian
 * @Date 2020/1/16 17:55:34
 */
public class ArrayBlockingQueueTest {

    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<Integer>(2);
        for(int i=1;i<13;i++){
            arrayBlockingQueue.add(i);
        }


    }
}
