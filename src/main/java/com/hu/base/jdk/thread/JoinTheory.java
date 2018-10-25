package com.hu.base.jdk.thread;

/**
 * @Author hutiantian
 * @Date 2018/10/24 17:52:39
 */
public class JoinTheory {



    public static void main(String[] args) {

        Thread A, B, C;
        A = new Thread("Thread_A") {
            public void run() {
                for (int i = 1; i <= 3; i++) {
                    System.out.println(Thread.currentThread().getName() + "  sleep " + i + "s");
                    try {
                        Thread.sleep(1000);
//                        allThread();
                    } catch (Exception e) {

                    }
                }
            }
        };
        B = new Thread("Thread_B") {
            public void run() {
                try {
                    A.join();
                    synchronized (A) {
                        A.wait();
                    }
                    for (int i = 1; i <= 3; i++) {
                        System.out.println(Thread.currentThread().getName() + " sleep " + i + "s");
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {

                }
            }
        };
         C = new Thread("Thread_C"){
            public void run(){
                try{
//                    B.join();
                    synchronized(A){
                        A.wait();
                    }
                    for (int i = 1; i <=3 ; i++) {
                        System.out.println(Thread.currentThread().getName()+"  sleep "+i+"s");
                        Thread.sleep(1000);
                    }
                }catch (Exception e){

                }
            }
        };
        A.start();
        B.start();
        allThread();
//        C.start();

    }




    private static void allThread() {
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup = group;
        // 遍历线程组树，获取根线程组
        while (group != null) {
            topGroup = group;
            group = group.getParent();
        }
        // 激活的线程数加倍
        int estimatedSize = topGroup.activeCount() * 2;
        Thread[] slackList = new Thread[estimatedSize];
        // 获取根线程组的所有线程
        int actualSize = topGroup.enumerate(slackList);
        // copy into a list that is the exact size
        Thread[] list = new Thread[actualSize];
        System.arraycopy(slackList, 0, list, 0, actualSize);
        System.out.println("Thread list size == " + list.length);
        for (Thread thread : list) {
            if(thread.getName().contains("Thread")){
                System.out.println(thread.getName());
            }
        }
    }
}
