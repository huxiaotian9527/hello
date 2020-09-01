package com.hu.base.jdk.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulePool {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(new Run(), 10000,
                2000, TimeUnit.MILLISECONDS);
    }


    public static class Run implements Runnable{
        public void run(){
            System.out.println("132456");
        }
    }
}
