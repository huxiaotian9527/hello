package com.hu.base.test;

/**
 * @Author hutiantian
 * @Date 2018/10/9 16:22:36
 */
public class Father {
    static{
        System.out.println("father static");
    }

    Father(){
        System.out.println("father construct");
    }
    {
        System.out.println("father nonStruct");
    }
}
