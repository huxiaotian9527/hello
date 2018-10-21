package com.hu.base.util.pdf;

import java.io.*;

/**
 * @Author hutiantian
 * @Date 2018/10/21 14:43:22
 */
public class Test {

    public static void main(String[] args) throws Exception{
        Measure measure = new Measure();
        measure.setReplaceName("合同编号");
        measure.setAfterName("合同编号：rpc调用后拿到的123456789");
        measure.setReplaceIndex(-1);
        ByteArrayOutputStream bao = PdfUtil.handler(new FileInputStream("D:\\原始合同.pdf"),measure,null);
        FileOutputStream fos = new FileOutputStream("D:\\pdfUtil.pdf");
        fos.write(bao.toByteArray());
        fos.close();

    }


}
