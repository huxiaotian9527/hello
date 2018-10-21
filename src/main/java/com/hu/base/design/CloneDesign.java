package com.hu.base.design;

import lombok.Data;

import java.io.*;

/**
 * @Author hutiantian
 * @Date 2018/10/18 20:24:51
 */
@Data
public class CloneDesign implements Cloneable, Serializable {
    private String name ;
    private A test;

    public static void main(String[] args) throws Exception{
        CloneDesign a = new CloneDesign();
        a.setName("haha");
        A test = new A();
        test.setStr("sb");
        a.setTest(test);
//        CloneDesign b = (CloneDesign)a.clone();
        CloneDesign b = (CloneDesign)a.deepClone();
        b.getTest().setStr("wobushisb");
        b.setName("hehe");
        System.out.println(a.name);
        System.out.println(a.getTest().getStr());
        System.out.println(b.name);
        System.out.println(b.getTest().getStr());
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
    //深克隆
    public Object deepClone() throws Exception {
        Object b = null;
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bo);
        oos.writeObject(this);
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        return oi.readObject();
    }

}
