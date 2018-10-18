package com.hu.base.design;

/**
 * 双重加锁单例
 * @Author hutiantian
 * @Date 2018/10/18 9:05:58
 */
public class Singleton {
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
