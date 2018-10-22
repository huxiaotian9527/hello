package com.hu.base.util.pdf;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * @Author hutiantian
 * @Date 2018/10/21 14:43:22
 */
public class Test {

    public static void main(String[] args) throws Exception{
        Measure measure = new Measure();
        measure.setAfterName("合同编号：rpc调用后拿到的123456789");
        ArrayList<String> list = new ArrayList<String>();
        list.add("测试张三");
        list.add("420607199305068981");
        list.add("10,005.00");
        list.add("suishou0000198786");
        list.add("测试李四");
        list.add("420607199305068981");
        list.add("18,225.88");
        list.add("suishou0000198788");
        list.add("测试哈哈");
        list.add("420607199305068981");
        list.add("18,888.88");
        list.add("suishou0000198788");

        list.add("哈哈");
        list.add("420607199305068981");
        list.add("18,225.88");
        list.add("suishou0000198788");
        for (int i = 0; i < 50; i++) {
            list.add("测试李四");
            list.add("420607199305068981");
            list.add("18,225.88");
            list.add("suishou0000198788");
        }

        long startTime = System.currentTimeMillis();
        ByteArrayOutputStream bao = PdfUtil.handler(new FileInputStream("D:\\原始合同.pdf"),measure,list);
        long endTime = System.currentTimeMillis();
        System.out.println("花费的时间为："+(endTime-startTime)+"ms");


        FileOutputStream fos = new FileOutputStream("D:\\pdfUtil.pdf");
        fos.write(bao.toByteArray());
        fos.close();
    }


}
