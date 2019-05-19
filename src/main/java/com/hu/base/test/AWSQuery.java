package com.hu.base.test;

import com.hu.base.util.http.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hutiantian
 * @date: 2019/5/19 23:03
 * @since 1.0.0
 */
public class AWSQuery {

    public static String url= "https://www.amazon.de/dp/";

    public static void main(String[] args) throws Exception{
        //从ali云拷出来的apache2访问日志
        String file = "C:\\Users\\Administrator\\Desktop\\ran.log";
        List<String> list = getList();
        for(String str:list){
            //随便从百度找的一个查找ip的网站，F12 network看了下是get请求，拼装url就ok
            Document doc = Jsoup.connect(url+str).get();
            Elements clearfix = doc.getElementsByAttribute("SalesRank");  //选择器的形式
            System.out.println(clearfix.toString());
            System.out.println();


        }

    }

    public static List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("B075GKB2V1");
        list.add("B00Y8AHZYC");
        list.add("B00W3I82L6");
        list.add("B00YMI0KEM");
        list.add("B074BSWHX8");
        return list;
    }
}
