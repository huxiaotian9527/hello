package com.hu.base.collection;

/**
 * 斐波那契算法
 *
 * @Author hutiantian
 * @Date 2018/9/30 16:21:56
 */
public class Fibonacci {

    public static void main(String[] args) {
        long sTime = System.currentTimeMillis();
        long result = fib1(1000);
        long eTime = System.currentTimeMillis();
        System.out.println("计算结果为：" + result + "所耗时间为：" + (eTime - sTime)  + "s");
    }

    /**
     * 最简单的实现,网上说时间复杂度为O(2n)，实际并不是
     * 经过实验发现效率太低，n过大的时候会栈溢出，n=50就得花37s，效率太低了
     * 并且n=50已经超过int最大值了
     */
    public static long fib(long n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    /**
     * 也是比较简单的实现，效率很高，真正的O(n)
     */
    public static long fib1(long n) {
        long pre = 1;       //前一个值
        long suf = 1;       //后一个值
        long res = 0;       //结果值
        if (n <= 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            for (int i = 3; i < n + 1; i++) {
                res = pre + suf;
                pre = suf;
                suf = res;
            }
            return res;
        }
    }
}
