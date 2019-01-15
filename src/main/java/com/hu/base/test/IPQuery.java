package com.hu.base.test;

import com.hu.base.util.http.HttpUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 分许博客系统access.log访问记录中的地区分布
 * @author hutiantian
 * @date: 2019/1/15 20:36
 * @since 1.0.0
 */
public class IPQuery {
    public static void main(String[] args) throws Exception{
        //从ali云拷出来的apache2访问日志
        String file = "C:\\Users\\Administrator\\Desktop\\access.log";
        //key:ip value访问次数
        Map<String,Integer> ipMap = new HashMap<>();
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String str;
        while((str=bf.readLine())!=null){
            String temp = str.split(" ")[0];
            if(ipMap.get(temp)==null){
                ipMap.put(temp,0);        //将访问者ip放入map
            }
            ipMap.put(temp,ipMap.get(temp)+1);        //将访问者ip放入map
        }
        for(Map.Entry<String,Integer> entry:ipMap.entrySet()){
            //随便从百度找的一个查找ip的网站，F12 network看了下是get请求，拼装url就ok
            String address = HttpUtil.doGet("http://www.ip138.com/ips1388.asp?ip="+entry.getKey()+"&action=2");
            //这个网站返回的是Content-Type是<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
            //查询源码httpclient没有解析到charset=gb2312，用的默认的编码ISO-8859-1，所以这里得转化一下编码格式
            address  = new String(address.getBytes("ISO-8859-1"),"gb2312");
            //根据关键字截取ip的地址
            String ss = address.split("<td align=\"center\"><ul class=\"ul1\"><li>本站数据：")[1].substring(0,6);
            System.out.println("IP："+entry.getKey()+"  访问次数："+entry.getValue()+"  地址："+ss);
            //随眠一下，免得被别人拒绝服务0.o
            Thread.sleep(200);
        }

    }
}
