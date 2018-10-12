package com.hu.base.test;

/**
 * @Author hutiantian
 * @Date 2018/10/9 16:22:36
 */
public class Father implements Cloneable{
    void testDefault(){
        System.out.println("default method");
    }

    protected void testProtected(){
        System.out.println("protect method");
    }

    public void testPublic(){
        System.out.println("pub");
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
