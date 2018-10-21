package com.hu.base.test;

import lombok.Cleanup;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @Author hutiantian
 * @Date 2018/9/20 13:40:24
 */
public class FileRead {


    public static void main(String[] args) throws Exception{
        @Cleanup BufferedReader bf = new BufferedReader(new FileReader("D:\\1.txt"));
        String str;
        while((str=bf.readLine())!=null){
            String[] sa = str.split("\t");
            System.out.println("private String "+sa[0]+";"+"\t\t//"+sa[1]+","+sa[2]);
//            System.out.println("novaReqBo.set"+sa[0].substring(0,1).toUpperCase()+sa[0].substring(1)+"(\"\");"+"\t\t//"+sa[1]+","+sa[2]);
        }
        bf.close();
    }
}
