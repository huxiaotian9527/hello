package com.hu.base.test;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author hutiantian
 * @Date 2018/9/18 15:27:33
 */
@Slf4j
@Data
@ToString
public class Test implements Cloneable {
    public String a;
    public String b;


    public static void main(String[] args) throws Exception {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String endDate = sdf.format(date);
        System.out.println(endDate);
    }


    /**
     * 反转字符串
     */
    public static void inversionString(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int t = (int) chars[i];
            if (t > 64 && t < 90) {
                chars[i] += 32;
            } else if (t > 97 && t < 122) {
                chars[i] -= 32;
            }
        }
        System.out.println(new String(chars));
    }



}
