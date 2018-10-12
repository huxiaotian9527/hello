package com.hu.base.io;

import com.hu.base.test.Father;

/**
 * @Author hutiantian
 * @Date 2018/10/12 9:59:07
 */
public class B extends Father{

    public static void main(String[] args) {
        Father f = new Father();
        f.testPublic();
    }


    public void test(){
        super.testProtected();
    }
}
