package com.hu.base.util.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author hutiantian
 * @Date 2018/9/17 14:08:31
 */
public class StackOverFlow {

    public static void manipulatePdf(String src, String dest) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(src);
        Rectangle pagesize = reader.getPageSize(7);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        PdfPTable table = new PdfPTable(2);
        table.addCell("#");
        table.addCell("description");
        table.setHeaderRows(1);
        table.setWidths(new int[]{ 1, 15 });
        for (int i = 1; i <= 50; i++) {
            table.addCell(String.valueOf(i));
            table.addCell("test " + i);
        }
        ColumnText column = new ColumnText(stamper.getOverContent(7));
        Rectangle rectPage1 = new Rectangle(38, 106, 559, 540);
        column.setSimpleColumn(rectPage1);
        column.addElement(table);
        int pagecount = 7;
        Rectangle rectPage2 = new Rectangle(38, 106, 559, 806);
        int status = column.go();
        while (ColumnText.hasMoreText(status)) {
            status = triggerNewPage(stamper, pagesize, column, rectPage2, ++pagecount);
        }
        stamper.setFormFlattening(true);
        stamper.close();
        reader.close();
    }

    public static int triggerNewPage(PdfStamper stamper, Rectangle pagesize, ColumnText column, Rectangle rect, int pagecount) throws DocumentException {
        stamper.insertPage(pagecount, pagesize);
        PdfContentByte canvas = stamper.getOverContent(pagecount);
        column.setCanvas(canvas);
        column.setSimpleColumn(rect);
        return column.go();
    }

    public static void main(String[] args) throws Exception{
        manipulatePdf("D:\\2.pdf","D:\\6.pdf");
    }
}
