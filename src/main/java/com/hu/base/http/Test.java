package com.hu.base.http;

import com.hu.base.http.util.DebtHttpRequest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @Author hutiantian
 * @Date 2018/12/21 13:49:19
 */
public class Test {

    public static void main(String[] args) throws Exception{
        String url = "https://marketres.ssjlicai.com/fnc_archive_fbfile/7B/9B/CssgE1vROiOEPZvGAAAAAH3IppM004.pdf";
        ByteArrayOutputStream bao = DebtHttpRequest.download(url);
        FileOutputStream fos = new FileOutputStream(new File("D:\\1.pdf"));
        fos.write(bao.toByteArray());
        fos.close();
    }
}
