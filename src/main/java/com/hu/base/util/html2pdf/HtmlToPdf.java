package com.hu.base.util.html2pdf;

import com.itextpdf.text.pdf.BaseFont;
import lombok.extern.slf4j.Slf4j;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author hutiantian
 * @Date 2019/1/4 17:42:11
 */

/**
 * @Author hutiantian
 * @Date 2018/11/14 16:40:42
 */
@Slf4j
public class HtmlToPdf {

    private static final String charset = "utf-8";
    /**
     * 字体-宋体
     */
    private static final String SIMSUN = "SimSun.ttc";
    /**
     * 字体-黑体
     */
    private static final String SIMHEI = "SimHei.ttf";
    /**
     *  字体-Arial
     */
    private static final String ARIAL = "Arial.ttf";

    /**
     * 根据html模板和map数据，生产pdf文档
     */
    public static byte[] getPdf(String fileType, Map<String,String> map) throws Exception{
        byte[] original = HtmlFactory.getHtml(fileType);
        String html = new String(original,charset);
        for(Map.Entry<String,String> entry : map.entrySet()){
            html = html.replace("@"+entry.getKey()+"@",entry.getValue());
        }
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        ITextFontResolver fontResolver = renderer.getFontResolver();
        // 宋体字
        fontResolver.addFont(buildAbsoluteFilePath("D:\\Arial.ttf") + SIMSUN, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        // 黑体
        fontResolver.addFont(buildAbsoluteFilePath("D:\\SimHei.ttf") + SIMHEI, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        // Arail
        fontResolver.addFont(buildAbsoluteFilePath("D:\\SimSun.ttc") + ARIAL, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(bao);
        return bao.toByteArray();
    }

    public static void main(String[] args) throws Exception{
        FileOutputStream os = new FileOutputStream("D:\\test.pdf");
        HashMap<String,String> map = new HashMap<>();
        map.put("name1","张三一");
        map.put("name2","张三一");
        map.put("name3","李四");
        map.put("name4","2018年1月1日");
        map.put("name5","2019年1月1日");
        map.put("name6","10000.00");
        map.put("name7","2019年1月1日");
        map.put("name8","中华保险");
        map.put("name9","10000.00");
        map.put("name10","王白丹");
        map.put("name11","2019年1月1日");
        map.put("name12","找不能");
        map.put("name13","急急急");
        map.put("name14","10000.00");
        map.put("name15","10000.00");
        map.put("name16","2019年1月1日");
        map.put("name17","BD201811150000085");
        os.write(HtmlToPdf.getPdf(FileConstant.CLAIM_FILE,map));
        os.close();
    }

    private static String buildAbsoluteFilePath(String type) {
        String pdfFileRootDir = "";
        String fontFileRootDir = "";
        String apath = pdfFileRootDir;
        if ("PDF".equalsIgnoreCase(type)) {
            apath = pdfFileRootDir;
        }
        if ("FONT".equalsIgnoreCase(type)) {
            apath = fontFileRootDir;
        }
        if (!apath.endsWith(File.separator)) {
            apath += File.separator;
        }
        return apath;
    }
}

