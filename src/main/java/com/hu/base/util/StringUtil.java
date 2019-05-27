package com.hu.base.util;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author hutiantian
 * @Date 2019/5/27 11:28:23
 */
public class StringUtil {

    /**
     * 将json字符串中的下滑线转成驼峰法，只转化key中的下划线，value中的不能转换
     */
    private static String parseMsg(String msg){
        Map<String,Object> map = JSON.parseObject(msg);
        Map<String,Object> temp = new HashMap<>();
        Iterator<Map.Entry<String,Object>> it = map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,Object> entry = it.next();
            String before = entry.getKey();
            String after = underlineToCamel(before);
            if(!before.equals(after)){
                temp.put(after,entry.getValue());
                it.remove();
            }
        }
        for(String key : temp.keySet()){
            map.put(key,temp.get(key));
        }
        return JSON.toJSONString(map);
    }

    /**
     * 字符串下划线转驼峰
     */
    private static String underlineToCamel(String param) {
        char underLine='_';
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = Character.toLowerCase(param.charAt(i));
            if (c == underLine) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
