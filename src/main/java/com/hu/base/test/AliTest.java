package com.hu.base.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hutiantian
 * @date: 2019/1/1 17:20
 * @since 1.0.0
 */
public class AliTest {


    public static void main(String[] args) throws Exception{
        Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();
        new Thread(new MyThread(lock,conditionA,conditionB,"A")).start();
        Thread.sleep(10);
        new Thread(new MyThread(lock,conditionB,conditionC,"B")).start();
        Thread.sleep(10);
        new Thread(new MyThread(lock,conditionC,conditionA,"C")).start();
    }

    public static class MyThread implements Runnable{
        private Lock lock;
        private Condition before;
        private Condition after;
        private String str;

        public MyThread(Lock lock,Condition before,Condition after,String str){
            this.lock = lock;
            this.before = before;
            this.after = after;
            this.str = str;
        }

        public void run(){
            lock.lock();
            try{
                for (int i = 0; ; i++) {
                    System.out.println(str);
                    Thread.sleep(600);
                    after.signal();
                    before.await();
                }
            }catch (Exception e){}
            finally {
                lock.unlock();
            }
        }
    }
}
