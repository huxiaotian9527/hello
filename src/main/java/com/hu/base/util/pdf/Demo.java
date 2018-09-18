package com.hu.base.util.pdf;

import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author hutiantian
 * @Date 2018/9/17 9:52:00
 */
public class Demo {
    // 定义关键字
    private static String KEY_WORD = "合同编号:";
    private static String KEY_WORD1 = "合同编号：";
    private static String KEY_WORD2 = "合同编号 ";
    // 定义返回页码
    private static int i = 0;

    private static int size = 10;
    private static float[] resu = null;

    private static float defaultH = 12;        //出现无法取到值的情况，默认为12
    private static float fixHeight = 2;        //可能出现无法完全覆盖的情况，提供修正的参数，默认为2
    private static float width = 1000;        //需要替换的长度

    public static void main(String[] args) throws Exception {
        String originPDF = "D:\\1.pdf";
        String handlePDF = "D:\\2.pdf";
        manipulatePdf(originPDF,handlePDF);
        String changePDF = "D:\\3.pdf";
        String tablePDF = "D:\\4.pdf";
        PdfReader reader = new PdfReader(handlePDF);
        reader.selectPages("1,2,3,4,5,6");
        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(changePDF));
        stamp.close();
        reader.close();
        newTablePDF(tablePDF);
        mergePdf(changePDF,tablePDF,"D:\\5.pdf");
    }

    public static void mergePdf(String file1, String file2, String fileFinal) {
        try {
            String[] files = {file1, file2};
            Document pDFCombineUsingJava = new Document();
            PdfCopy copy = new PdfCopy(pDFCombineUsingJava, new FileOutputStream(fileFinal));
            pDFCombineUsingJava.open();
            PdfReader ReadInputPDF;
            for (int i = 0; i < files.length; i++) {
                ReadInputPDF = new PdfReader(files[i]);
                copy.addDocument(ReadInputPDF);
                copy.freeReader(ReadInputPDF);
            }
            pDFCombineUsingJava.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void newTablePDF(String file) throws Exception {
        FileOutputStream outputStream = new FileOutputStream(file);
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
        Font font=new Font(bf,13,Font.NORMAL);
        PdfPTable table=new PdfPTable(4);
        //设置各列的列宽
        table.setTotalWidth(new float[]{150,300,200,250});
        //添加表格内容
        PdfPCell cell=mergeCol("出借人清单", font, 4);
        table.addCell(cell);
        table.addCell(getPDFCell("出借人姓名",font));
        table.addCell(getPDFCell("身份证号",font));
        table.addCell(getPDFCell("出借金额",font));
        table.addCell(getPDFCell("随手 ID",font));

        table.addCell(getPDFCell("测试张三",font));
        table.addCell(getPDFCell("420607199305068981",font));
        table.addCell(getPDFCell("10,005.00",font));
        table.addCell(getPDFCell("suishou0000198786",font));
        table.addCell(getPDFCell("测试李四",font));
        table.addCell(getPDFCell("420607199305068981",font));
        table.addCell(getPDFCell("18,225.88",font));
        table.addCell(getPDFCell("suishou0000198788",font));
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

    /*
     * 返回关键字所在的坐标和页数 float[0] >> X float[1] >> Y float[2] >> page
     */
    private static float[] getKeyWords(PdfReader pdfReader) {
        try {
            int pageNum = pdfReader.getNumberOfPages();
            PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(pdfReader);
            // 下标从1开始
            for (i = 1; i < pageNum; i++) {
                pdfReaderContentParser.processContent(i, new RenderListener() {
                    public void renderText(TextRenderInfo textRenderInfo) {
                        String text = textRenderInfo.getText();
                        if (null != text && (text.contains(KEY_WORD) || text.contains(KEY_WORD1) || text.contains(KEY_WORD2))) {
                            Rectangle2D.Float boundingRectangle = textRenderInfo.getBaseline().getBoundingRectange();
                            resu = new float[5];
                            resu[0] = i;
                            resu[1] = boundingRectangle.x;
                            resu[2] = boundingRectangle.y - fixHeight;
                            resu[3] = boundingRectangle.width;
                            resu[4] = boundingRectangle.height == 0 ? defaultH : boundingRectangle.height;
                        }
                    }
                    public void renderImage(ImageRenderInfo arg0) {
                        // TODO Auto-generated method stub
                    }
                    public void endTextBlock() {
                        // TODO Auto-generated method stub
                    }
                    public void beginTextBlock() {
                        // TODO Auto-generated method stub
                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resu;
    }

    public static void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        float[] resu = getKeyWords(reader);
        FileOutputStream outputStream = new FileOutputStream(dest);
        PdfStamper stamper = new PdfStamper(reader, outputStream);
        PdfContentByte canvas = stamper.getOverContent((int) resu[0]);
        canvas.saveState();
        canvas.setColorFill(BaseColor.WHITE);
//        canvas.rectangle(resu[1], resu[2], width, resu[4]);
        canvas.rectangle(resu[1], resu[2], width, resu[4]);
        canvas.fill();
        canvas.restoreState();
        //开始写入文本
        canvas.beginText();
        //BaseFont bf = BaseFont.createFont(URLDecoder.decode(CutAndPaste.class.getResource("/AdobeSongStd-Light.otf").getFile()), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
        Font font = new Font(bf, size, Font.NORMAL);
        //设置字体和大小
        canvas.setFontAndSize(font.getBaseFont(), size);
        //设置字体的输出位置
        canvas.setTextMatrix(resu[1], resu[2] + 2);
        //要输出的text
        canvas.showText("合同编号：rpc调用后拿到的123456789");
//        ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, new Phrase("合同编号：rpc调用后拿到的123456789",font), resu[1], resu[2], 0);
        canvas.endText();
        stamper.close();
        reader.close();
    }
}
