package com.hu.base.test;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

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
        final ArrayList<String> list = new ArrayList<>();
        System.out.println(list.hashCode());
        for (int i = 0; i <100 ; i++) {
            list.add("tiantian");
        }
        System.out.println(list.hashCode());
    }


}
