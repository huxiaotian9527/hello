package com.hu.base.test;

/**
 * @Author hutiantian
 * @Date 2018/10/9 16:23:59
 */
public class Son extends Father {
    static{
        System.out.println("son static");
    }

    {
        System.out.println("son nonStatic");
    }

    Son(){
        System.out.println("son Construct");
    }

    public static void main(String[] args) {
        new Son();
    }
}
