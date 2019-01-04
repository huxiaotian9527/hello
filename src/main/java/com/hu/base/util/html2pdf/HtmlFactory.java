package com.hu.base.util.html2pdf;


import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @Author hutiantian
 * @Date 2018/11/14 16:42:04
 */
@Slf4j
public class HtmlFactory {
    private static HashMap<String, byte[]> htmlMap = new HashMap<>();
    private static String claimFile = "pdf"+File.separator+"claim.html";                   //索赔申请书
    private static String payNotifyFile = "pdf"+File.separator+"pay_notify.html";          //出险通知书
    private static String payProtocolFile ="pdf"+ File.separator+"pay_protocol.html";      //赔款权益转让书

    static {
        try {
            htmlMap.put(FileConstant.CLAIM_FILE, getBytes(claimFile));
            htmlMap.put(FileConstant.NOTIFY_FILE, getBytes(payNotifyFile));
            htmlMap.put(FileConstant.PROTOCOL_FILE, getBytes(payProtocolFile));
        } catch (Exception e) {
            log.error("加载html文件失败！", e);
        }
    }

    /**
     * 获取html模板的字节数组
     * @param type
     * @return
     */
    public static byte[] getHtml(String type) {
        byte[] original = htmlMap.get(type);
        int length = original.length;
        byte[] res = new byte[length];
        System.arraycopy(original,0,res,0,length);
        return res;
    }

    /**
     * 读取文件中的字节数组
     */
    private static byte[] getBytes(String filePath) throws IOException {
        InputStream fis = HtmlFactory.class.getClassLoader().getResourceAsStream(filePath);
        byte[] buf;
        try {
            buf = new byte[fis.available()];
            fis.read(buf);
        } finally {
            fis.close();
        }
        return buf;
    }
}
