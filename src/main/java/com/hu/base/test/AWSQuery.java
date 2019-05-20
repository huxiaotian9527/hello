package com.hu.base.test;

import com.hu.base.util.DateFormatEnum;
import com.hu.base.util.DateUtil;
import com.hu.base.util.ExcelUtil;
import com.hu.base.util.http.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author hutiantian
 * @date: 2019/5/19 23:03
 * @since 1.0.0
 */
public class AWSQuery {

    public static String url= "https://www.amazon.de/dp/";

    public static void main(String[] args) throws Exception{
        //获取需要处理的商品集合
        List<String> goodsList = getList();
        List<List<String>> excelList = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(goodsList.size());
        for(String str:goodsList){
            new Thread(()->{
                //jsoup解析dom
                Document doc = Jsoup.parse(HttpUtil.doGet(url+str).trim());
                Element salesRank = doc.getElementById("SalesRank");  //选择器的形式
                String[] strings = salesRank.text().split("Nr.");
                List<String> strList = new ArrayList<>();
                strList.add(str);
                for (int i = 1; i < strings.length; i++) {
                    //把这种排名转换 19.883 in Garten (Siehe Top 100 in Garten)
                    //成 Sport & Freizeit + 8.215
                    String rank = strings[i];
                    String str1 = rank.split("in")[0].trim();
                    String str2 = rank.replace(str1+" in","").trim();
                    if(str2.contains("(")){
                        str2 = str2.split("\\(")[0].trim();
                    }
                    strList.add(str2);
                    strList.add(str1);
                }
                synchronized (AWSQuery.class){
                    excelList.add(strList);
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        int max = 0;
        //取最长的一列，其它补空
        for (List<String> list : excelList){
            if(list.size()>max){
                max = list.size();
            }
        }
        for (List<String> list : excelList){
            int size = list.size();
            if(size<max){
                for (int i = 0; i < max-size; i++) {
                    list.add("");
                }
            }
        }
        generateExcel(excelList);
    }

    /**
     * 从文件中读取需要获取信息的商品id
     */
    public static List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("B075GKB2V1");
        list.add("B00Y8AHZYC");
        list.add("B00W3I82L6");
        list.add("B00YMI0KEM");
        list.add("B074BSWHX8");
        return list;
    }

    /**
     * 生成excel
     */
    private static void generateExcel(List<List<String>> list) throws Exception{
        List<String> headList = new ArrayList<>();
        headList.add("ASIN");
        headList.add("大类名称");
        headList.add("大类排名");
        int num = (list.get(0).size()-3)/2;
        for (int i = 1; i < num+1; i++) {
            headList.add("小类名称"+i);
            headList.add("小类排名"+i);
        }
        String  sDate = DateUtil.toString(new Date(), DateFormatEnum.YEAR_MONTH_DAY);
        String filename = "D:\\AWS商品信息"+sDate+".xls";
        OutputStream out = new FileOutputStream(filename); // 输出目的地
        ExcelUtil.ReportList("aws rank", "AWS商品信息",headList, list, out);
    }
}
