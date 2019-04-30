package com.hu.base.jdk.serialize;

import java.io.Serializable;

/**
 * @Author hutiantian
 * @Date 2019/4/22 11:38:28
 */

public class Student implements Serializable {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
