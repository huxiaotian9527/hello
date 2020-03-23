package com.hu.base.jdk.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author hutiantian
 * @Date 2018/11/1 20:07:55
 */
public class ConditionTest {


    public static void main(String[] args) throws Exception {
        Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();
        new Thread(new ThreadTest(lock, conditionA, conditionB, "A")).start();
        Thread.sleep(10);
        new Thread(new ThreadTest(lock, conditionB, conditionC, "B")).start();
        Thread.sleep(10);
        new Thread(new ThreadTest(lock, conditionC, conditionA, "C")).start();

    }

    static class ThreadTest implements Runnable{
        Lock lock;
        Condition condition1;
        Condition condition2;
        String str;

        ThreadTest(Lock lock,Condition condition1,Condition condition2,String str){
            this.condition1 = condition1;
            this.condition2 = condition2;
            this.lock = lock;
            this.str = str;
        }

        public void run(){
            lock.lock();
            try {
                int i = 0;
                for ( ; i < 11; i++) {
                    System.out.println(Thread.currentThread().getName()+":"+str);
                    if(i<10){
                        condition2.signal();
                        condition1.await();
                    }else {
                        condition2.signal();
                    }
                }

            }catch (Exception e){

            }finally {
                lock.unlock();
            }
        }
    }






}
