package com.hu.base.test;

import com.hu.base.util.DateFormatEnum;
import com.hu.base.util.DateUtil;
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


    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat sdf = new SimpleDateFormat(DateFormatEnum.YEAR_MONTH_DAY_HH_MM_SS.toString());
        String endDate = sdf.format(date);
        System.out.println(endDate);
        Date d = DateUtil.toDate(endDate, DateFormatEnum.YEAR_MONTH_DAY_HH_MM_SS);
        System.out.println(DateUtil.dayDiff(date,DateUtil.nextDays(d,-15)));
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
