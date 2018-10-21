package com.hu.base.util.pdf;

import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    private static final int FONT_SIZE = 10;                                //字体大小

    /**
     * 根据measure，差异化处理pdf文件
     *
     * @param is      要处理的pdf输入流
     * @param measure 处理策略，根据不同的机构会有不一样的策略
     * @param list    需要追加的出借人清单列表
     * @return 处理后的pdf字节流
     */
    public static ByteArrayOutputStream handler(InputStream is, Measure measure, List list) {
        try {
            //1.替换合同编号
            ByteArrayOutputStream afterBao = new ByteArrayOutputStream();           //替换后的字节流
            replaceContractNo(is, afterBao, measure);
            //2.追加出借人清单


        } catch (Exception e) {
            log.error("pdf合同转换处理失败!", e);
        }
        return null;
    }

    /**
     * 找指定页的关键字坐标
     * @param pdfReader 需要操作的pdfReader
     * @param page 查找的页数
     * @param key 查找的关键字
     * @param fixHeight 坐标修正
     * @param defaultHeight 默认高度
     * @return
     * @throws IOException
     */
    private static ArrayList<float[]> getKeyWords(PdfReader pdfReader, int page,String key,float fixHeight,float defaultHeight) throws IOException {
        float[] result = new float[4];
        ArrayList list = new ArrayList();       //可能会有多个匹配
        PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(pdfReader);
        pdfReaderContentParser.processContent(page, new RenderListener() {
            public void renderText(TextRenderInfo textRenderInfo) {
                String text = textRenderInfo.getText();
                if (null != text && text.contains(key)) {
                    Rectangle2D.Float boundingRectangle = textRenderInfo.getBaseline().getBoundingRectange();
                    result[0] = boundingRectangle.x;
                    result[1] = boundingRectangle.y - fixHeight;
                    result[2] = boundingRectangle.width;
                    result[3] = boundingRectangle.height == 0 ? defaultHeight : boundingRectangle.height;
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
        return list;
    }

    /**
     * 替换合同编号所在行，如果没有匹配到关键字，则不替换
     * @param is      需要处理的流
     * @param os      处理完之后的流
     * @param measure 参数配置类
     * @throws Exception
     */
    private static void replaceContractNo(InputStream is, OutputStream os, Measure measure) throws Exception {
        byte[] bytes = getBytes(is);
        PdfReader reader = new PdfReader(bytes);
        //返回关键字所在的坐标和页数
        ArrayList<float[]> list = getKeyWords(reader, measure.getFindPage(),measure.getReplaceName(),
                                                      measure.getFixHeight(),measure.getDefaultHeight());
        //如果没有，则将输入流不处理写入到输出流
        if (list == null || list.size() == 0 ||measure.getReplaceIndex() > list.size()) {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            bao.write(bytes, 0, bytes.length);
            os = bao;               //将os的指针指向bao
            reader.close();
            return;
        }
        //取出指定下标的关键字
        if(measure.getReplaceIndex()<0){
            measure.setReplaceIndex(list.size()-1);
        }
        float[] result = list.get(measure.getReplaceIndex());
        PdfStamper stamper = new PdfStamper(reader, os);
        PdfContentByte canvas = stamper.getOverContent(measure.getFindPage());
        canvas.saveState();
        canvas.setColorFill(BaseColor.WHITE);
        canvas.rectangle(result[0], result[1], measure.getReplaceWidth(), result[3]);
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
        canvas.setTextMatrix(result[0], result[1] + measure.getFixHeight());
        //要输出的text
        canvas.showText(measure.getAfterName());
        canvas.endText();
        stamper.close();
        reader.close();
    }

    /**
     * 读取输入流中的字节数组
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
}
