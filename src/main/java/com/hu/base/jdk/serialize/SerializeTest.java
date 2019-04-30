package com.hu.base.jdk.serialize;

import java.io.*;

/**
 * @Author hutiantian
 * @Date 2019/4/22 11:39:18
 */
public class SerializeTest {

    public static void main(String[] args) throws Exception {
        Student s = new Student();
        s.setAge(10);
        s.setName("胡天天");
        File f = new File("D:\\student.txt");
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(f));
        os.writeObject(s);
        os.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
        Student x = (Student)in.readObject();
        System.out.println(x.getAge());
        System.out.println(x.getName());
    }
}
