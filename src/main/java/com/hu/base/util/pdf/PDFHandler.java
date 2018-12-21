package com.hu.base.util.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author hutiantian
 * @Date 2018/9/17 14:46:59
 */
public class PDFHandler {

    private static float defaultH = 12;        //出现无法取到值的情况，默认为12
    private static float fixHeight = 2;        //可能出现无法完全覆盖的情况，提供修正的参数，默认为2
    private static float width = 1000;         //需要替换的长度
    private static int size = 10;              //字体大小


    /*
     * 判断制定页码是否含有关键字，如果有同时返回关键字坐标
     */
    private static Coordinate getKeyWord(final String key, int num, PdfReader pdfReader) {
        final ArrayList<Coordinate> list = new ArrayList<Coordinate>();
        try {
            PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(pdfReader);
            throw new Exception();
//            pdfReaderContentParser.processContent(num, new RenderListener() {
//                public void renderText(TextRenderInfo textRenderInfo) {
//                    String text = textRenderInfo.getText();
//                    if (null != text && text.contains(key)) {
//                        Rectangle2D.Float boundingRectangle = textRenderInfo.getBaseline().getBoundingRectange();
//                        Coordinate coordinate = new Coordinate();
//                        coordinate.setX(boundingRectangle.x);
//                        coordinate.setY(boundingRectangle.y - fixHeight);
//                        coordinate.setWidth(boundingRectangle.width);
//                        coordinate.setHeight(boundingRectangle.height == 0 ? defaultH : boundingRectangle.height);
//                        list.add(coordinate);
//                    }
//                }
//
//                public void renderImage(ImageRenderInfo arg0) {
//                }
//
//                public void endTextBlock() {
//                }
//
//                public void beginTextBlock() {
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 生成借款人表格
     */
    public static PdfPTable generateLoanTable(ArrayList<String> list) throws Exception {
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
        for(String str:list){
            table.addCell(getPDFCell(str,font));
        }
        return table;
    }

    /**
     * 获取指定内容与字体的单元格
     */
    public static PdfPCell getPDFCell(String string, Font font) {
        //创建单元格对象，将内容与字体放入段落中作为单元格内容
        PdfPCell cell = new PdfPCell(new Paragraph(string, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        //设置最小单元格高度
        cell.setMinimumHeight(25);
        return cell;
    }

    /**
     * 合并列的静态函数
     */
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
     * 追加新的pdf页
     */
    public static int triggerNewPage(PdfStamper stamper, Rectangle pagesize, ColumnText column, Rectangle rect, int pagecount) throws DocumentException {
        stamper.insertPage(pagecount, pagesize);
        PdfContentByte canvas = stamper.getOverContent(pagecount);
        column.setCanvas(canvas);
        column.setSimpleColumn(rect);
        return column.go();
    }

    public static void main(String[] args) throws Exception{
        replaceKeyword("D:\\123.pdf","D:\\2.pdf");
        appendLoanTable("D:\\2.pdf","D:\\3.pdf");
    }

    public static void replaceKeyword(String src, String dest) throws IOException, DocumentException {
//        PdfReader reader = new PdfReader(src);
//        Coordinate coordinate = getKeyWord("合同编号：",1,reader);
//        //如果在第一页找到匹配的关键字，就替换
//        if(coordinate!=null){
//            FileOutputStream outputStream = new FileOutputStream(dest);
//            PdfStamper stamper = new PdfStamper(reader, outputStream);
//            PdfContentByte canvas = stamper.getOverContent(1);
//            canvas.saveState();
//            canvas.setColorFill(BaseColor.WHITE);
//            canvas.rectangle(coordinate.getX(), coordinate.getY(), width, coordinate.getHeight());
//            canvas.fill();
//            canvas.restoreState();
//            //开始写入文本
//            canvas.beginText();
//            //BaseFont bf = BaseFont.createFont(URLDecoder.decode(CutAndPaste.class.getResource("/AdobeSongStd-Light.otf").getFile()), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
//            Font font = new Font(bf, size, Font.NORMAL);
//            //设置字体和大小
//            canvas.setFontAndSize(font.getBaseFont(), size);
//            //设置字体的输出位置
//            canvas.setTextMatrix(coordinate.getX(), coordinate.getY() - fixHeight);
//            //要输出的text
//            canvas.showText("合同编号：rpc调用后拿到的123456789");
//            canvas.endText();
//            stamper.close();
//        }
//        reader.close();
    }

    public static void appendLoanTable(String src, String dest) throws Exception {
        PdfReader reader = new PdfReader(src);
        int pagecount = reader.getNumberOfPages();
        Rectangle pageSize = reader.getPageSize(pagecount);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        ArrayList<String> list = new ArrayList<String>();
        list.add("测试张三");
        list.add("420607199305068981");
        list.add("10,005.00");
        list.add("0000198786");
        for (int i = 0; i < 100; i++) {
            list.add("12345678");
        }
        PdfPTable table = generateLoanTable(list);
        ColumnText column = new ColumnText(stamper.getOverContent(pagecount));
        //查找指定的关键字
        Coordinate coordinate = getKeyWord("出借人清单",pagecount,reader);
        if(coordinate!=null){
            //需要把以前的给遮盖住
            PdfContentByte canvas = column.getCanvas();
            canvas.saveState();
            canvas.setColorFill(BaseColor.WHITE);
//            canvas.rectangle(0, 0, width, coordinate.getY()+30);
            canvas.fill();
            canvas.restoreState();
//            Rectangle rectPage1 = new Rectangle(38, 100, 559, coordinate.getY()+15);
//            column.setSimpleColumn(rectPage1);
            column.addElement(table);
            Rectangle rectPage2 = new Rectangle(38, 100, 559, 806);
            int status = column.go();
            while (ColumnText.hasMoreText(status)) {
                status = triggerNewPage(stamper, pageSize, column, rectPage2, ++pagecount);
            }
        }else {
            //TODO 找不到追加处理
            Rectangle rectPage1 = new Rectangle(38, -500, 559, -500);
            column.setSimpleColumn(rectPage1);
            column.addElement(table);
            Rectangle rectPage2 = new Rectangle(38, 100, 559, 806);
            int status = column.go();
            while (ColumnText.hasMoreText(status)) {
                status = triggerNewPage(stamper, pageSize, column, rectPage2, ++pagecount);
            }
        }
        stamper.setFormFlattening(true);
        stamper.close();
        reader.close();
    }
}
