package com.hu.base.test;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author hutiantian
 * @Date 2018/9/18 15:27:33
 */
@Slf4j
@Data
@ToString
public class Test implements Cloneable{
    public String a;
    public String b;


    public static void main(String[] args) throws Exception {
        int a = 5;
        int b = ++a;
        System.out.println(b);
        System.out.println(a);
    }


}
