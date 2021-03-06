package com.hu.base.util.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * @Author hutiantian
 * @Date 2018/10/21 14:43:22
 */
public class Test {

    public static void main(String[] args) throws Exception{
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            list.add("测试李四");
            list.add("420************981");
            list.add("18,225.88");
            list.add("0000198788");
        }


        String fileName = "";
        File files = new File("D:\\");
        for(File f :files.listFiles()){
            if(f.getName().endsWith("处理后.pdf")){
                f.delete();
                break;
            }
            if(f.getName().endsWith("pdf")){
                fileName = f.getName().substring(0,f.getName().length()-4);
            }
        }
        String orgNo;
        switch(fileName){
            case "达飞协议":
                orgNo = "C0024";
                break;
            case "功夫贷合同":
                orgNo = "C0021";
                break;
            default:
                orgNo = "C0035";
                break;
        }
        long startTime = System.currentTimeMillis();
        ByteArrayOutputStream bao = PdfUtil.handler(new FileInputStream("D:\\"+fileName+".pdf"),orgNo,list);
        long endTime = System.currentTimeMillis();
        System.out.println("花费的时间为："+(endTime-startTime)+"ms");


        FileOutputStream fos = new FileOutputStream("D:\\"+fileName+"_处理后.pdf");
        fos.write(bao.toByteArray());
        fos.close();
    }


}
