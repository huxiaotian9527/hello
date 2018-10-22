package com.hu.base.util.pdf;

import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * pdf工具类
 *
 * @Author hutiantian
 * @Date 2018/10/21 11:03:36
 */
@Slf4j
public class PdfUtil {

    private static final String FONT_NAME = "STSong-Light";                 //字体名称
    private static final String FONT_ENCODING = "UniGB-UCS2-H";             //字体编码
    private static final boolean FONT_EMBEDDED = true;                      //是否嵌套
    private static final int FONT_SIZE = 12;                                //字体大小

    /**
     * 根据measure，差异化处理pdf文件
     *
     * @param is       要处理的pdf输入流
     * @param measure  处理策略，根据不同的机构会有不一样的策略
     * @param loanList 需要追加的出借人清单列表
     * @return 处理后的pdf字节流
     */
    public static ByteArrayOutputStream handler(InputStream is, Measure measure, List<String> loanList) {
        try {
            //1.替换合同编号
            ByteArrayOutputStream afterContractBao = replaceContractNo(is, measure);           //替换后的字节流

            //2.身份证号脱敏
            ByteArrayOutputStream afterCertBao = replaceCertNo(afterContractBao, measure);

            //3.追加出借人清单
            return linkLoanList(afterCertBao, measure, loanList);

        } catch (Exception e) {
            log.error("pdf合同转换处理失败!", e);
        }
        return null;
    }

    /**
     * 替换合同编号所在行，如果没有匹配到关键字，则不替换
     *
     * @param is      需要处理的流
     * @param measure 参数配置类
     * @return 处理后的字节流
     * @throws Exception
     */
    private static ByteArrayOutputStream replaceContractNo(InputStream is, Measure measure) throws Exception {
        byte[] bytes = getBytes(is);
        PdfReader reader = new PdfReader(bytes);
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        //返回关键字所在的坐标
        List<Coordinate> list = getKeyWords(reader, measure.getFindPage(), measure.getReplaceName(),
                measure.getFixHeight(), measure.getDefaultHeight());
        //如果没有，则将输入流不处理写入到输出流
        if (list == null || list.size() == 0) {
            bao.write(bytes, 0, bytes.length);
            reader.close();
            return bao;
        }
        //取出指定下标的关键字
        if (measure.getReplaceIndex() < 0 || measure.getReplaceIndex() > list.size()) {
            measure.setReplaceIndex(list.size() - 1);
        }
        Coordinate coordinate = list.get(measure.getReplaceIndex());
        PdfStamper stamper = new PdfStamper(reader, bao);
        PdfContentByte canvas = stamper.getOverContent(measure.getFindPage());
        canvas.saveState();
        canvas.setColorFill(BaseColor.WHITE);
        canvas.rectangle(coordinate.getC1().getX(), coordinate.getC1().getY(), measure.getReplaceWidth(), coordinate.getC1().getHeight());
        canvas.fill();
        canvas.restoreState();
        //开始写入文本
        canvas.beginText();
        //创建字体
        BaseFont bf = BaseFont.createFont(FONT_NAME, FONT_ENCODING, FONT_EMBEDDED);
        //字体大小
        Font font = new Font(bf, FONT_SIZE, Font.NORMAL);
        //设置字体和大小
        canvas.setFontAndSize(font.getBaseFont(), FONT_SIZE);
        //设置字体的输出位置
        canvas.setTextMatrix(coordinate.getC1().getX(), coordinate.getC1().getY());
        //要输出的text
        canvas.showText(measure.getAfterName());
        canvas.endText();
        stamper.close();
        reader.close();
        return bao;
    }


    /**
     * 替换身份证所在行，如果没有匹配到关键字，则不替换
     */
    private static ByteArrayOutputStream replaceCertNo(ByteArrayOutputStream baos, Measure measure1) throws Exception {
        byte[] bytes = baos.toByteArray();
        PdfReader reader = new PdfReader(bytes);
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        PdfStamper stamper = new PdfStamper(reader, bao);
        for(CertNoConfig config:measure1.getConfigs()){
            //返回关键字所在的坐标
            List<Coordinate> list = getKeyWords(reader, config.getPage() , config.getName(),
                    config.getFixHeight(), config.getDefaultHeight());
            if (list == null || list.size() == 0) {
                continue;
            }
            //取出指定下标的关键字
            if (config.getIndex() < 0 || config.getIndex() > list.size()) {
                config.setIndex(list.size() - 1);
            }
            Coordinate coordinate = list.get(config.getIndex());
            PdfContentByte canvas = stamper.getOverContent(config.getPage());
            canvas.saveState();
            canvas.setColorFill(BaseColor.WHITE);
            canvas.rectangle(coordinate.getC1().getX()+config.getXOffset(), coordinate.getC1().getY(), config.getWidth(), coordinate.getC1().getHeight());
            canvas.fill();
            canvas.restoreState();
            //开始写入文本
            canvas.beginText();
            //创建字体
            BaseFont bf = BaseFont.createFont(FONT_NAME, FONT_ENCODING, FONT_EMBEDDED);
            //字体大小
            Font font = new Font(bf, config.getFontSize(), Font.NORMAL);
            //设置字体和大小
            canvas.setFontAndSize(font.getBaseFont(), config.getFontSize());
            //设置字体的输出位置
            canvas.setTextMatrix(coordinate.getC1().getX()+config.getXOffset(), coordinate.getC1().getY()+config.getYOffset());
            //要输出的text
            canvas.showText(config.getText());
            canvas.endText();
        }
        stamper.close();
        reader.close();
        return bao;
    }


    /**
     * 找指定页的关键字坐标
     *
     * @param pdfReader     需要操作的pdfReader
     * @param page          查找的页数
     * @param key           查找的关键字
     * @param fixHeight     坐标修正
     * @param defaultHeight 默认高度
     * @return
     * @throws IOException
     */
    private static List<Coordinate> getKeyWords(PdfReader pdfReader, int page, String key, float fixHeight, float defaultHeight) throws IOException {
        List<Coordinate> list = new ArrayList();       //可能会有多个匹配
        PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(pdfReader);
        pdfReaderContentParser.processContent(page, new RenderListener() {
            public void renderText(TextRenderInfo textRenderInfo) {
                String text = textRenderInfo.getText();
                if (null != text && text.contains(key)) {
                    Rectangle2D.Float boundingRectangle = textRenderInfo.getBaseline().getBoundingRectange();
                    //新建一个坐标类
                    Coordinate coordinate = new Coordinate();
                    Coordinate.C1 c1 = new Coordinate.C1();
                    coordinate.setC1(c1);
                    c1.setX(boundingRectangle.x);
                    c1.setY(boundingRectangle.y - fixHeight);
                    c1.setWidth(boundingRectangle.width);
                    c1.setHeight(boundingRectangle.height == 0 ? defaultHeight + fixHeight : boundingRectangle.height + fixHeight);
                    list.add(coordinate);
                }
            }

            public void renderImage(ImageRenderInfo arg0) {
            }

            public void endTextBlock() {
            }

            public void beginTextBlock() {
            }
        });
        return list;
    }

    /**
     * 读取输入流中的字节数组
     *
     * @param is 输入流
     * @return 字节数组
     * @throws IOException
     */
    private static byte[] getBytes(InputStream is) throws IOException {
        byte[] buf;
        try {
            buf = new byte[is.available()];
            is.read(buf);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buf;
    }

    /**
     * 追加出借人清单
     *
     * @param bao      原始pdf流
     * @param measure  参数配置类
     * @param loanList 需要追加的出借人清单
     * @return
     * @throws Exception
     */
    private static ByteArrayOutputStream linkLoanList(ByteArrayOutputStream bao, Measure measure, List<String> loanList) throws Exception {
        ByteArrayOutputStream afterBao;
        //直接生成新的sheet页
        if (StrategyEnum.NEW.equals(measure.getStrategy())) {
            afterBao = replaceOrNewMode(bao, measure, loanList, 1);
        }
        //根据坐标替换源文件的出借人清单，生成新的清单覆盖
        else {
            afterBao = replaceOrNewMode(bao, measure, loanList, 0);
        }
        return afterBao;
    }

    /**
     * 替换模式+新生成模式
     *
     * @param bao      待处理的pdf流
     * @param measure  参数配置类
     * @param loanList 出借人列表
     * @param type     0-替换模式；1-新生成模式
     * @return 处理后的pdf流
     * @throws Exception
     */
    private static ByteArrayOutputStream replaceOrNewMode(ByteArrayOutputStream bao, Measure measure, List<String> loanList, int type) throws Exception {
        PdfReader reader = new PdfReader(bao.toByteArray());        //拿到pdfReader
        int pageCount = reader.getNumberOfPages();                  //pdf总页数
        Rectangle pageSize = reader.getPageSize(pageCount);
        //新建结果bao
        ByteArrayOutputStream resultBao = new ByteArrayOutputStream();
        PdfStamper stamper = new PdfStamper(reader, resultBao);
        ColumnText column = new ColumnText(stamper.getOverContent(pageCount));  //追加
        //生成借款人PdfPTable
        PdfPTable table = generateLoanTable(loanList);
        Rectangle rectPage1;                                    //当前页坐标
        if (type == 1) {                                            //NEW模式，直接新起一页追加
            rectPage1 = new Rectangle(0, 0, 1, 1);
        } else {                                                 //REPLACE模式，在当前页追加
            //查找关键字所在的坐标
            List<Coordinate> list = getKeyWords(reader, measure.getLoanPage() < 0 ? pageCount : measure.getLoanPage(), measure.getLoanName(),
                    measure.getFixHeight(), measure.getDefaultHeight());
            //如果没有找到，则使用StrategyEnum.NEW策略
            if (list == null || list.size() == 0) {
                rectPage1 = new Rectangle(0, 0, 1, 1);
            } else {
                //取出指定下标的关键字
                if (measure.getLoanIndex() < 0 || measure.getLoanIndex() > list.size()) {
                    measure.setLoanIndex(list.size() - 1);
                }
                Coordinate coordinate = list.get(measure.getLoanIndex());       //关键字坐标
                //需要把以前的给遮盖住
                PdfContentByte canvas = column.getCanvas();
                canvas.saveState();
                canvas.setColorFill(BaseColor.WHITE);
                canvas.rectangle(0, 0, measure.getReplaceWidth(), coordinate.getC1().getY() + measure.getLoanHeight());
                canvas.fill();
                canvas.restoreState();
                rectPage1 = new Rectangle(measure.getRePageLast().getX1(), measure.getRePageLast().getY1(),
                        measure.getRePageLast().getX2(), coordinate.getC1().getY() + measure.getNewLoanHeight());
            }
        }
        column.setSimpleColumn(rectPage1);
        column.addElement(table);
        //追加页坐标
        Rectangle rectPage2 = new Rectangle(measure.getRePageNew().getX1(), measure.getRePageNew().getY1(),
                measure.getRePageNew().getX2(), measure.getRePageNew().getY2());
        int status = column.go();
        while (ColumnText.hasMoreText(status)) {
            status = triggerNewPage(stamper, pageSize, column, rectPage2, ++pageCount);
        }
        stamper.setFormFlattening(true);
        stamper.close();
        reader.close();
        return resultBao;
    }

    /**
     * 追加新的pdf页
     */
    public static int triggerNewPage(PdfStamper stamper, Rectangle pageSize, ColumnText column, Rectangle rect, int pageCount) throws DocumentException {
        stamper.insertPage(pageCount, pageSize);
        PdfContentByte canvas = stamper.getOverContent(pageCount);
        column.setCanvas(canvas);
        column.setSimpleColumn(rect);
        return column.go();
    }

    /**
     * 生成借款人表格
     */
    public static PdfPTable generateLoanTable(List<String> list) throws Exception {
        BaseFont bf = BaseFont.createFont(FONT_NAME, FONT_ENCODING, FONT_EMBEDDED);
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
        for (String str : list) {
            table.addCell(getPDFCell(str, font));
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


}
