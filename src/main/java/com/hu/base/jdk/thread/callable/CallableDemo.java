package com.hu.base.jdk.thread.callable;

import java.util.concurrent.*;

/**
 * @Author hutiantian
 * @Date 2018/11/6 9:29:16
 */
public class CallableDemo {

    static class SumTask implements Callable<Long> {

        @Override
        public Long call(){

            long sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }

            return sum;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Start:" + System.nanoTime());
//        FutureTask<Long> futureTask = new FutureTask<Long>(new SumTask());
        Executor executor=Executors.newSingleThreadExecutor();
        FutureTask<Long> futureTask  = (FutureTask<Long>)((ExecutorService) executor).submit((new SumTask()));
        System.out.println(futureTask.get());
        System.out.println("End:" + System.nanoTime());
        ((ExecutorService) executor).shutdown();
    }
}
