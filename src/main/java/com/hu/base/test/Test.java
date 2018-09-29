package com.hu.base.test;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @Author hutiantian
 * @Date 2018/9/18 15:27:33
 */
@Slf4j
public class Test {
    public String ss;
    public int a;


    public static void main(String[] args) throws Exception {


        Map<String, String> map = new HashMap<String, String>();
        map.put("key", "11");
        map.put("key2", "22");
        map.put("key3", "33");
        map.put("key4", "xx");
        map.put("bey4", "");
        map.put("akey4", "wdwe");
        map.put("xakey4", "rwer");
        map.put("axkey4", "rwrew");
        System.out.println(JSON.toJSONString(map));
        //将key做字典排序
//        Map<String,Object> resultMap = JSON.parseObject(JSON.toJSONString(novaReqBo));
//        Collection<String> keySet = map.keySet();
//        List<String> list = new ArrayList<String>(keySet);
//        Collections.sort(list);
//        StringBuilder sign = new StringBuilder();                   //待签名数据
//        for(String key:list){
//            sign.append(key+"="+map.get(key)+"&");
//        }
//        System.out.println(sign.toString().substring(0,sign.length()-1));

    }

    public void test(){

    }
}
