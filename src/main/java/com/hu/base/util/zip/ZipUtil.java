package com.hu.base.util.zip;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @Author hutiantian
 * @Date 2018/11/20 15:38:29
 */
public class ZipUtil {

    /**
     * @param inputFileName
     *            你要压缩的文件夹(整个完整路径)
     * @param zipFileName
     *            压缩后的文件(整个完整路径)
     */
    public static void zip(String inputFileName, String zipFileName)
            throws Exception {
        zip(new File(inputFileName) ,zipFileName);
    }

    private static void zip(File inputFile, String zipFileName)
            throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
                zipFileName));
        out.setEncoding(System.getProperty("sun.jnu.encoding"));
        zip(out, inputFile, "");
        out.close();
    }

    private static void zip(ZipOutputStream out, File f, String base)
            throws Exception {
        if (f.isDirectory()) {
            File[] fl = f.listFiles();
            base = base.length() == 0 ? "" : base + "/"; // 注意，这里用左斜杠
            for (int i = 0; i < fl.length; i++) {
                zip(out, fl[i], base + fl[i].getName());
            }
        } else {
            out.putNextEntry(new ZipEntry(base));
            FileInputStream in = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            int n = in.read(buffer);
            while (n != -1) {
                out.write(buffer, 0, n);
                n = in.read(buffer);
            }
            in.close();
        }
    }

    public static void main(String[] temp) throws Exception{

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ZipOutputStream out = new ZipOutputStream(bao);
        out.setEncoding(System.getProperty("sun.jnu.encoding"));
        out.putNextEntry(new ZipEntry("D:\\测试"));
        byte[] bytes = getFile();
        out.write(bytes);
        out.close();
    }

    private static byte[] getFile() throws Exception{
        FileInputStream fis = new FileInputStream(new File("D:\\中互金数据上报.pptx"));
        byte[] bytes = new byte[1024*1024];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        int a = fis.read(bytes);
        bao.write(bytes,0,a);
        fis.close();
        return bao.toByteArray();
    }

}