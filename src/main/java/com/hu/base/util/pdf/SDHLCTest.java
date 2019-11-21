package com.hu.base.util.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @Author hutiantian
 *
 * @Date 2019/11/21 14:04:23
 */
public class SDHLCTest {

    public static void main(String[] args) throws Exception{
        String fileName = "123";
        String keyWord = "甲方";
        ByteArrayOutputStream bao = handler(new FileInputStream("D:\\"+fileName+".pdf"),keyWord);
        FileOutputStream fos = new FileOutputStream("D:\\"+fileName+"_1.pdf");
        fos.write(bao.toByteArray());
        fos.close();
//        test(fileName+"_1");


    }



    public static  ByteArrayOutputStream handler(InputStream is,String keyWord) throws Exception{
        byte[] bytes= PdfUtil.getBytes(is);
        ByteArrayOutputStream resultBao = new ByteArrayOutputStream();
        PdfReader reader = new PdfReader(bytes);        //拿到pdfReader
        //查找关键字所在的坐标
        List<Coordinate> list = PdfUtil.getKeyWords(reader, 4, keyWord, 5,12);

        Coordinate coordinate = list.get(list.size() - 1);       //关键字坐标
        PdfStamper stamper = new PdfStamper(reader, resultBao);
        ColumnText column = new ColumnText(stamper.getOverContent(4));  //追加
        //需要把以前的给遮盖住
//        PdfContentByte canvas = column.getCanvas();
//        canvas.saveState();
//        canvas.setColorFill(BaseColor.WHITE);
//        canvas.rectangle(0, 0, 1000, 2000);
//        canvas.fill();
//        canvas.restoreState();
        PdfContentByte canvas = column.getCanvas();
        canvas.saveState();
        canvas.setColorFill(BaseColor.WHITE);
        canvas.rectangle(0, 0, 1000, coordinate.getC1().getY()+17);

//        OCGParser.removeLayers();
        //截取页面
        reader.selectPages("1,2,3,4");
        canvas.fill();
        canvas.restoreState();
        stamper.setFormFlattening(true);
        stamper.close();
        reader.close();
        return resultBao;
    }


    public static void test(String fileName) throws Exception{
        FileOutputStream out = new FileOutputStream("D:\\"+fileName+".pdf");

        Document document = new Document();

        PdfWriter writer = PdfWriter.getInstance(document, out);


        PdfReader reader = new PdfReader("D:\\"+fileName+".pdf");
        reader.selectPages("1,2,3,4");
        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream("D:\\"+fileName+"_2"+".pdf"));
        stamp.close();
        reader.close();
    }
}
