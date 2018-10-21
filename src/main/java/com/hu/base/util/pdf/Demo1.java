package com.hu.base.util.pdf;

import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

import java.io.*;
import java.util.ArrayList;

/**
 * @Author hutiantian
 * @Date 2018/9/17 9:52:00
 */
public class Demo1 {
    private static int i = 0;

    private static float defaultH = 12;        //出现无法取到值的情况，默认为12
    private static float fixHeight = 2;        //可能出现无法完全覆盖的情况，提供修正的参数，默认为2
    private static float width = 1000;        //需要替换的长度

    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();       //字节流

        String originPDF = "D:\\原始合同.pdf";
        manipulatePdf(readFile(originPDF), baos);
        PdfReader reader = new PdfReader(baos.toByteArray());
        int num = reader.getNumberOfPages();        //合同总合同
        reader.selectPages(getPages(num));          //截取前n-1页
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();       //字节流
        PdfStamper stamp = new PdfStamper(reader, baos1);
        stamp.close();
        reader.close();
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();       //字节流
        newTablePDF(baos2);
        ByteArrayOutputStream baos3 = new ByteArrayOutputStream();       //字节流
        //将截取的合同与新生成的合同拼接
        mergePdf1(baos1.toByteArray(), baos2.toByteArray(), baos3);
        System.out.println(baos3.size());
        FileOutputStream fos = new FileOutputStream("D:\\baos合同.pdf");
        fos.write(baos3.toByteArray());
        fos.close();
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
     * 将合同 B 追加至合同 A
     *
     * @param fileA     合同 A
     * @param fileB     合同 B
     * @param finalFile 合同 A+B
     */
    public static void mergePdf1(byte[] fileA, byte[] fileB, OutputStream os) throws Exception {
        byte[][] files = {fileA, fileB};
        Document pDFCombineUsingJava = new Document();
        PdfCopy copy = new PdfCopy(pDFCombineUsingJava, os);
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
     * 新生成一个带出借人清单表格的合同
     */
    public static void newTablePDF(OutputStream os) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, os);
        document.open();
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
        Font font = new Font(bf, 13, Font.NORMAL);
        PdfPTable table = new PdfPTable(4);
        //设置各列的列宽
        table.setTotalWidth(new float[]{150, 300, 200, 250});
        //添加表格内容
        PdfPCell cell = mergeCol("出借人清单", font, 4);
        table.addCell(cell);
        table.addCell(getPDFCell("出借人姓名", font));
        table.addCell(getPDFCell("身份证号", font));
        table.addCell(getPDFCell("出借金额", font));
        table.addCell(getPDFCell("随手 ID", font));

        table.addCell(getPDFCell("测试张三", font));
        table.addCell(getPDFCell("420607199305068981", font));
        table.addCell(getPDFCell("10,005.00", font));
        table.addCell(getPDFCell("suishou0000198786", font));
        table.addCell(getPDFCell("测试李四", font));
        table.addCell(getPDFCell("420607199305068981", font));
        table.addCell(getPDFCell("18,225.88", font));
        table.addCell(getPDFCell("suishou0000198788", font));
        document.newPage();//新创建一页来存放后面生成的表格
        document.add(table);
        document.close();
    }

    //获取指定内容与字体的单元格
    public static PdfPCell getPDFCell(String string, Font font) {
        //创建单元格对象，将内容与字体放入段落中作为单元格内容
        PdfPCell cell = new PdfPCell(new Paragraph(string, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        //设置最小单元格高度
        cell.setMinimumHeight(25);
        return cell;
    }

    //合并列的静态函数
    public static PdfPCell mergeCol(String str, Font font, int i) {
        PdfPCell cell = new PdfPCell(new Paragraph(str, font));
        cell.setMinimumHeight(25);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        //将该单元格所在行包括该单元格在内的i列单元格合并为一个单元格
        cell.setColspan(i);
        return cell;
    }

    /**
     * 替换合同编号，原始合同的合同编号可能是错误的
     *
     * @param src  初始合同路径
     * @param dest 替换后的合同路径
     * @throws IOException
     * @throws DocumentException
     */
    public static void manipulatePdf(byte[] b,OutputStream os) throws Exception {

        PdfReader reader = new PdfReader(b);
        //返回关键字所在的坐标和页数
        ArrayList<float[]> list = getKeyWords(reader, "合同编号");
        //如果没有，不处理直接退出
        if (list == null || list.size() == 0) {
            return;
        }
        //默认取第一页
        float[] result = list.get(0);
        PdfStamper stamper = new PdfStamper(reader, os);
        PdfContentByte canvas = stamper.getOverContent((int) result[0]);
        canvas.saveState();
        canvas.setColorFill(BaseColor.WHITE);
        canvas.rectangle(result[1], result[2], width, result[4]);
        canvas.fill();
        canvas.restoreState();
        //开始写入文本
        canvas.beginText();
        //创建字体
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
        //字体大小
        int size = 10;
        Font font = new Font(bf, size, Font.NORMAL);
        //设置字体和大小
        canvas.setFontAndSize(font.getBaseFont(), size);
        //设置字体的输出位置
        canvas.setTextMatrix(result[1], result[2] + 2);
        //要输出的text
        canvas.showText("合同编号：rpc调用后拿到的123456789");
        canvas.endText();
        stamper.close();
        reader.close();
    }

    /*
     * 返回关键字所在的坐标和页数 page float[0] >> X float[1] >> Y float[2] >> width float[3] >> height float[4]
     */
    private static ArrayList<float[]> getKeyWords(PdfReader pdfReader, String key) throws Exception {
        float[] result = new float[5];
        ArrayList list = new ArrayList();
        int pageNum = pdfReader.getNumberOfPages();
        PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(pdfReader);
        // 下标从1开始
        for (i = 1; i < pageNum; i++) {
            pdfReaderContentParser.processContent(i, new RenderListener() {
                public void renderText(TextRenderInfo textRenderInfo) {
                    String text = textRenderInfo.getText();
                    if (null != text && text.contains(key)) {
                        Rectangle2D.Float boundingRectangle = textRenderInfo.getBaseline().getBoundingRectange();
                        result[0] = i;
                        result[1] = boundingRectangle.x;
                        result[2] = boundingRectangle.y - fixHeight;
                        result[3] = boundingRectangle.width;
                        result[4] = boundingRectangle.height == 0 ? defaultH : boundingRectangle.height;
                        list.add(result);
                    }
                }

                public void renderImage(ImageRenderInfo arg0) {
                }

                public void endTextBlock() {
                }

                public void beginTextBlock() {
                }
            });
        }
        return list;
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
}
