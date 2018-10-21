package com.hu.base.design;

/**
 * 双重加锁单例
 * @Author hutiantian
 * @Date 2018/10/18 9:05:58
 */
public class Singleton {
    /**
     * volatile的作用是禁止指令优化
     * 避免第一个线程在new Singleton的过程中
     * 1.分配地址 2 赋值 3 将地址引用赋给形参
     * cpu指令重排序后 singleton!=null 但是因引用没有值
     * 第二个线程判断非空，就i直接拿到这个没有赋值的引用地址
     */
    private static volatile Singleton singleton;         //单例模式的实例

    private Singleton() {                                //私有构造方法
    }

    public static Singleton getInstance() {
        if (singleton == null) {                        //为了提高效率，这里不加锁
            synchronized (Singleton.class) {            //加锁，避免多次构造实例
                if (singleton == null) {                //被阻塞的线程在进来后
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
