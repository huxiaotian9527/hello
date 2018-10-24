package com.hu.base.util.pdf;


import com.itextpdf.text.Document;
import com.itextpdf.text.exceptions.UnsupportedPdfException;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author hutiantian
 * @Date 2018/9/17 9:52:00
 */
public class Demo {
    private static int i = 0;

    public static void main(String[] args) throws Exception {
        String originPDF = "D:\\原始合同.pdf";
        String handlePDF = "D:\\替换关键字之后的合同.pdf";
        String changePDF = "D:\\截取前几页的合同.pdf";
        String tablePDF = "D:\\新生成的出界人清单合同.pdf";
        PdfReader reader = new PdfReader(handlePDF);
        int num = reader.getNumberOfPages();        //合同总合同
        reader.selectPages(getPages(num));          //截取前n-1页
        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(changePDF));
        stamp.close();
        reader.close();
        //将截取的合同与新生成的合同拼接
        mergePdf(changePDF, tablePDF, "D:\\最终合同.pdf");
        parse();

    }

    /**
     * 将合同 B 追加至合同 A
     *
     * @param fileA     合同 A
     * @param fileB     合同 B
     * @param finalFile 合同 A+B
     */
    public static void mergePdf(String fileA, String fileB, String finalFile) throws Exception {
        String[] files = {fileA, fileB};
        Document pDFCombineUsingJava = new Document();
        PdfCopy copy = new PdfCopy(pDFCombineUsingJava, new FileOutputStream(finalFile));
        pDFCombineUsingJava.open();
        PdfReader ReadInputPDF;
        for (int i = 0; i < files.length; i++) {
            ReadInputPDF = new PdfReader(files[i]);
            copy.addDocument(ReadInputPDF);
            copy.freeReader(ReadInputPDF);
        }
        pDFCombineUsingJava.close();
    }




    /**
     * 获取合同前num-1页，从第一页开始
     */
    private static String getPages(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < num; i++) {
            sb.append(i + ",");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    /**
     * 从文件中读取字节流
     * @param path
     * @return
     */
    public static byte[] readFile(String path){
        File file = new File(path);
        FileInputStream input = null;
        try{
            input = new FileInputStream(file);
            byte[] buf =new byte[input.available()];
            input.read(buf);
            return buf;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(input != null){
                try {
                    input.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 截取pdfFile的第from页至第end页，组成一个新的文件名
     * @param respdfFile  需要分割的PDF
     * @param savepath  新PDF
     * @param from  起始页
     * @param end  结束页
     */
    public static void splitPDFFile(String respdfFile,String savepath, int from, int end) throws Exception{
        Document document = null;
        PdfCopy copy = null;
        try {
            PdfReader reader = new PdfReader(respdfFile);
            int n = reader.getNumberOfPages();
            if(end==0){
                end = n;
            }
            ArrayList<String> savepaths = new ArrayList<String>();
            String staticpath = respdfFile.substring(0, respdfFile.lastIndexOf("\\")+1);
            //String savepath = staticpath+ newFile;
            savepaths.add(savepath);
            document = new Document(reader.getPageSize(1));
            copy = new PdfCopy(document, new FileOutputStream(savepaths.get(0)));
            document.open();
            for(int j=from; j<=end; j++) {
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, j);
                copy.addPage(page);
            }
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取Pdf文件的内容
     */
    public static void readPdfContent() {
        try {
            PdfReader pr = new PdfReader("D:\\我来贷合同.pdf");
            int page = pr.getNumberOfPages();
            String content = "";
            for (int i = 1; i < page + 1; i++) {
                content += PdfTextExtractor.getTextFromPage(pr, i); //遍历页码,读取Pdf文件内容
            }
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static  void parse() throws IOException {
        String src = "D:\\1.pdf";
        String dest = "D:\\3.pdf";
        PdfReader reader = new PdfReader(src);
        PdfObject obj;
        for (int i = 1; i <= reader.getXrefSize(); i++) {
            obj = reader.getPdfObject(i);
            if (obj != null && obj.isStream()) {
                PRStream stream = (PRStream)obj;
                byte[] b;
                try {
                    b = PdfReader.getStreamBytes(stream);
                }
                catch(UnsupportedPdfException e) {
                    b = new byte[0];
                    System.out.println(e);
                }
                FileOutputStream fos = new FileOutputStream(dest);
                fos.write(b);
                fos.flush();
                fos.close();
            }
        }
    }
}
