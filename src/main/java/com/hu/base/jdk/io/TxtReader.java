package com.hu.base.jdk.io;

import java.io.File;

/**
 * @Author hutiantian
 * @Date 2018/10/19 10:53:22
 */
public class TxtReader {

    public static void main(String[] args) {
        readTxt(new File("D:\\logs"));
    }

    public static void readTxt(File file){
        for(File f :file.listFiles()){
            if(f.isDirectory()){
                readTxt(f);
            }else {
                if(f.getName().endsWith("txt")){
                    System.out.println(f.getPath()+f.getName());
                }
            }
        }


    }
}
